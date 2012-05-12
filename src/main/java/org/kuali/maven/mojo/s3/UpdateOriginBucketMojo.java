/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.mojo.s3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.maven.common.UrlBuilder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * <p>
 * This mojo updates a bucket serving as an origin for a Cloud Front distribution. It generates an html directory
 * listing for each "directory" in the bucket and stores the html under a key in the bucket such that a regular http
 * request for a directory returns html. What would happen otherwise is XML for "object does not exist" would be
 * returned by Amazon.
 * </p>
 * <p>
 * For example: The url "http://www.mybucket.com/foo/bar" will return an html page containing a listing of all the files
 * and directories under "foo/bar" in the bucket.
 * </p>
 * <p>
 * If a directory contains an object with a key that is the same as the default object, the plugin copies the object to
 * a key representing the directory structure.
 * </p>
 *
 * <p>
 * For example, the url "http://www.mybucket.com/foo/bar/index.html" represents an object in an S3 bucket under the key
 * "foo/bar/index.html". This plugin will copy the object from the key "foo/bar/index.html" to the key "foo/bar/". This
 * causes the url "http://www.mybucket.com/foo/bar/" to return the same content as the url
 * "http://www.mybucket.com/foo/bar/index.html"
 * </p>
 * <p>
 * It also generates an html directory listing at the root of the bucket hierarchy and places that html into the bucket
 * as the default object, unless a default object already exists.
 * </p>
 *
 * @goal updateoriginbucket
 */
public class UpdateOriginBucketMojo extends S3Mojo {
    UrlBuilder builder = new UrlBuilder();
    SimpleFormatter formatter = new SimpleFormatter();
    ThreadInvoker invoker = new ThreadInvoker();

    private static final String S3_INDEX_METADATA_KEY = "maven-cloudfront-plugin-index";
    private static final String S3_INDEX_CONTENT_TYPE = "text/html";
    CloudFrontHtmlGenerator generator;
    S3DataConverter converter;

    /**
     * GAV strings representing organizational poms eg "org.kuali.pom:kuali" or "org.kuali.pom:kuali-common". Version is
     * ignored, only groupId and artifactId are relevant.
     *
     * @parameter
     */
    private List<String> orgPomGavs;

    /**
     * The max number of threads to use when making calls to S3
     *
     * @parameter expression="${cloudfront.threads}" default-value="20"
     */
    private int threads;

    /**
     * This parameter should represent the portion of the groupId that is implied by the hostname where content is
     * published.
     *
     * For example, the Kuali Rice project has the groupId "org.kuali.rice" and content for the Kuali Rice project is
     * published under "site.kuali.org/rice". The "org.kuali" portion of the groupId is implied from the hostname
     * "site.kuali.org" and is thus removed when calculating the url in order to keep things a little more compact.
     *
     * If this parameter is not supplied, the complete groupId is used.
     *
     * @parameter expression="${cloudfront.organizationGroupId}"
     */
    private String organizationGroupId;

    /**
     * This controls the caching behavior for CloudFront. By default, CloudFront edge locations cache content from an S3
     * bucket for 24 hours. That interval is shortened to 1 hour for the html indexes generated by this plugin.
     *
     * @parameter expression="${cloudfront.cacheControl}" default-value="max-age=3600, must-revalidate"
     */
    private String cacheControl;

    /**
     * If true, "foo/bar/index.html" will get copied to "foo/bar/"
     *
     * @parameter expression="${cloudfront.copyDefaultObjectWithDelimiter}" default-value="true"
     */
    private boolean copyDefaultObjectWithDelimiter;

    /**
     * If true, "foo/bar/index.html" will get copied to "foo/bar". This is defaulted to false because the relative
     * pathing in the html generated by the maven-site-plugin does not render correctly from a url without the trailing
     * slash.
     *
     * @parameter expression="${cloudfront.copyDefaultObjectWithoutDelimiter}" default-value="false"
     */
    private boolean copyDefaultObjectWithoutDelimiter;

    /**
     * The stylesheet to use for the directory listing
     *
     * @parameter expression="${cloudfront.css}" default-value="http://s3browse.ks.kuali.org/css/style.css"
     */
    private String css;

    /**
     * Image representing a file
     *
     * @parameter expression="${cloudfront.fileImage}"
     *            default-value="http://s3browse.ks.kuali.org/images/page_white.png"
     */
    private String fileImage;

    /**
     * Image representing a directory
     *
     * @parameter expression="${cloudfront.directoryImage}"
     *            default-value="http://s3browse.ks.kuali.org/images/folder.png"
     */
    private String directoryImage;

    /**
     * When displaying the last modified timestamp, use this timezone
     *
     * @parameter expression="${cloudfront.timezone}" default-value="UTC"
     */
    private String timezone;

    /**
     * When displaying the last modified timestamp use this format
     *
     * @parameter expression="${cloudfront.dateFormat}" default-value="EEE, dd MMM yyyy HH:mm:ss z"
     */
    private String dateFormat;

    /**
     * The key containing the default object for the Cloud Front distribution. If this object already exists, the plugin
     * will not modify it. If it does not exist, this plugin will generate an html directory listing and place it into
     * the bucket under this key.
     *
     * @parameter expression="${cloudfront.defaultObjectKey}" default-value="index.html"
     */
    private String defaultObjectKey;

    /**
     * The html for browsing a directory will be created under this key
     *
     * @parameter expression="${cloudfront.browseKey}" default-value="browse.html"
     */
    private String browseKey;

    /**
     * The default permissions for S3 objects created by this plugin
     *
     * @parameter expression="${cloudfront.acl}" default-value="PublicRead"
     * @required
     */
    private CannedAccessControlList acl;

    /**
     * Return a listing of all the prefixes in this directory plus all the prefixes leading back to (and including) the
     * root directory
     *
     * @param listing
     * @param prefix
     * @param delimiter
     * @return
     */
    protected List<String> getPrefixes(ObjectListing listing, String prefix, String delimiter) {
        List<String> commonPrefixes = listing.getCommonPrefixes();
        List<String> pathPrefixes = getPathsToRoot(delimiter, prefix);
        List<String> prefixes = new ArrayList<String>();
        prefixes.addAll(commonPrefixes);
        prefixes.addAll(pathPrefixes);
        Collections.sort(prefixes);
        return prefixes;
    }

    /**
     * Get an object listing for the current directory
     *
     * @param context
     * @param request
     * @return
     */
    protected ObjectListing getObjectListing(S3BucketContext context, ListObjectsRequest request) {
        String prefix = getPrefix();
        String bucket = context.getBucket();
        String delimiter = context.getDelimiter();
        Integer maxKeys = context.getMaxKeys();

        // This is the file system equivalent of typing "ls" in a directory
        // No objects are retrieved, just metadata about the objects
        long start = System.currentTimeMillis();
        ObjectListing listing = context.getClient().listObjects(request);
        long millis = System.currentTimeMillis() - start;
        getLog().info(getListMsg(listing.getCommonPrefixes().size(), millis));

        // If we have more than 1000 objects in the current directory we have an issue
        if (listing.isTruncated()) {
            throw new AmazonServiceException("The listing for " + bucket + delimiter + prefix + " exceeded " + maxKeys);
        }
        return listing;
    }

    /**
     * Convert prefixes intl ListObjectContext objects
     *
     * @param bucketContext
     * @param prefixes
     * @return
     */
    protected List<ListObjectsContext> getListObjectsContexts(S3BucketContext bucketContext, List<String> prefixes) {
        List<ListObjectsContext> contexts = new ArrayList<ListObjectsContext>();
        for (String prefix : prefixes) {
            ListObjectsRequest request = getListObjectsRequest(bucketContext, prefix);
            ListObjectsContext context = new ListObjectsContext();
            context.setRequest(request);
            context.setBucketContext(bucketContext);
            contexts.add(context);
        }
        return contexts;
    }

    /**
     * Given a prefix and a bucket context return a ListObjectsRequest object
     *
     * @param context
     * @param prefix
     * @return
     */
    protected ListObjectsRequest getListObjectsRequest(S3BucketContext context, String prefix) {
        String bucket = context.getBucket();
        String delimiter = context.getDelimiter();
        Integer maxKeys = context.getMaxKeys();
        if (prefix.equals(delimiter)) {
            prefix = null;
        }
        ListObjectsRequest request = new ListObjectsRequest(bucket, prefix, null, delimiter, maxKeys);
        return request;
    }

    /**
     * Show some information about the current directory
     *
     * @param subDirectoryCount
     * @param millis
     * @return
     */
    protected String getListMsg(int subDirectoryCount, long millis) {
        StringBuilder sb = new StringBuilder();
        sb.append("S3 Directories: " + subDirectoryCount);
        sb.append("  Listing Request: " + formatter.getTime(millis));
        return sb.toString();
    }

    @Override
    public void executeMojo() throws MojoExecutionException, MojoFailureException {
        try {
            // Track what time we started
            long start = System.currentTimeMillis();
            getLog().info("Updating S3 bucket - " + getBucket());

            // Update properties on the mojo
            updateMojoState();

            // Save some context info about the bucket we are updating
            S3BucketContext context = getS3BucketContext();

            // Utilities for generating html
            generator = new CloudFrontHtmlGenerator(context);
            converter = new S3DataConverter(context);
            converter.setBrowseKey(getBrowseKey());

            // Show what we are up to
            String prefix = getPrefix();
            getLog().info("Re-indexing content for - " + prefix);

            // Get the object listing for this projects directory
            ListObjectsRequest request = getListObjectsRequest(context, prefix);

            // This object listing is a reflection of the projects directory out on S3
            ObjectListing projectDirectory = getObjectListing(context, request);

            // Get a list of prefixes representing other directories in this directory and the directories
            // leading back to (and including) the root directory
            List<String> prefixes = getPrefixes(projectDirectory, prefix, context.getDelimiter());

            // Make a multi-threaded call out to S3 to obtain object summaries for all of the prefixes
            List<ObjectListing> listings = getObjectListings(context, prefixes, threads);

            // Add the ObjectListing for the projects directory
            listings.add(projectDirectory);

            // Convert ObjectListings into S3PrefixContext objects (this generates the html)
            List<S3PrefixContext> contexts = getS3PrefixContexts(context, listings);

            // Convert S3PrefixContext objects into UpdateDirectoryContext objects
            List<UpdateDirectoryContext> udcs = getUpdateDirContexts(contexts);

            // Create a thread safe handler for dealing with elements in the list
            ElementHandler<UpdateDirectoryContext> handler = new UpdateDirectoryContextHandler(this);

            // Make a multi-threaded call out to S3. This creates and/or updates html indexes
            ExecutionStatistics stats = invoker.invokeThreads(threads, handler, udcs);

            // Print some diagnostics
            long millis = stats.getExecutionTime();
            long count = stats.getIterationCount();
            getLog().info("Updated " + count + " bucket keys.  Time: " + formatter.getTime(millis));
            getLog().info("S3 Bucket update complete - " + getBucket());
            getLog().info("Total time: " + formatter.getTime(System.currentTimeMillis() - start));
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error: ", e);
        }
    }

    /**
     * Make a multi-threaded call to S3 to acquire ObjectListing objects for each of the prefixes
     *
     * @param context
     * @param prefixes
     * @param maxThreads
     * @return
     */
    protected List<ObjectListing> getObjectListings(S3BucketContext context, List<String> prefixes, int maxThreads) {
        List<ListObjectsContext> contexts = getListObjectsContexts(context, prefixes);
        ListObjectsContextHandler elementHandler = new ListObjectsContextHandler();
        ExecutionStatistics stats = invoker.invokeThreads(maxThreads, elementHandler, contexts);
        long millis = stats.getExecutionTime();
        long count = stats.getIterationCount();
        getLog().info("Acquired listings for " + count + " prefixes.  Time: " + formatter.getTime(millis));
        return elementHandler.getObjectListings();
    }

    /**
     * Convert S3PrefixContext objects into UpdateDirectoryContext objects. Each S3PrefixContext object generates two S3
     * calls. One for the prefix with the delimiter and one for the prefix without the delimiter.
     *
     * @param contexts
     * @return
     */
    protected List<UpdateDirectoryContext> getUpdateDirContexts(List<S3PrefixContext> contexts) {
        List<UpdateDirectoryContext> list = new ArrayList<UpdateDirectoryContext>();
        for (S3PrefixContext context : contexts) {

            // Root object requires special handling
            if (context.isRoot()) {
                UpdateDirectoryContext udc = new UpdateDirectoryContext();
                udc.setContext(context);
                list.add(udc);
                continue;
            }

            // Create context info for prefixes with and without the delimiter
            String delimiter = context.getBucketContext().getDelimiter();
            String trimmedPrefix = converter.getTrimmedPrefix(context.getPrefix(), delimiter);

            UpdateDirectoryContext udc1 = new UpdateDirectoryContext();
            udc1.setContext(context);
            udc1.setCopyIfExists(isCopyDefaultObjectWithDelimiter());
            udc1.setCopyToKey(context.getPrefix());

            UpdateDirectoryContext udc2 = new UpdateDirectoryContext();
            udc2.setContext(context);
            udc2.setCopyIfExists(isCopyDefaultObjectWithoutDelimiter());
            udc2.setCopyToKey(trimmedPrefix);

            list.add(udc1);
            list.add(udc2);

        }
        return list;
    }

    /**
     * Split the string up and return a list of Strings representing the path from the starting prefix back to (and
     * including) the root of the bucket.
     *
     * @param delimiter
     * @param startingPrefix
     * @return
     */
    protected List<String> getPathsToRoot(String delimiter, String startingPrefix) {
        List<String> list = new ArrayList<String>();

        list.add(delimiter);

        String[] prefixes = StringUtils.splitByWholeSeparator(startingPrefix, delimiter);
        String newPrefix = "";
        for (int i = 0; i < prefixes.length - 2; i++) {
            newPrefix += prefixes[i] + delimiter;
            list.add(newPrefix);
        }
        return list;
    }

    /**
     * Get a default prefix into the bucket from the project + groupId
     *
     * @param project
     * @param groupId
     * @return
     */
    protected String getDefaultPrefix(MavenProject project, String orgGroupId) {
        List<MavenProject> orgPoms = builder.getMavenProjects(orgPomGavs);
        return builder.getSitePath(project, orgPoms, orgGroupId);
    }

    /**
     * If they supplied a prefix use it. Otherwise generate one from the project metadata
     */
    protected void updatePrefix() {
        String s = getPrefix();
        if (StringUtils.isEmpty(s)) {
            s = getDefaultPrefix(getProject(), getOrganizationGroupId());
        }
        if (!s.endsWith(getDelimiter())) {
            s = s + getDelimiter();
        }
        setPrefix(s);
    }

    /**
     * Update the state of this mojo from the project metadata
     */
    protected void updateMojoState() throws MojoExecutionException {
        updateCredentials();
        validateCredentials();
        updatePrefix();
    }

    /**
     * Get context information about the bucket we are operating on
     */
    protected S3BucketContext getS3BucketContext() throws MojoExecutionException {
        AWSCredentials credentials = getCredentials();
        AmazonS3Client client = new AmazonS3Client(credentials);
        S3BucketContext context = new S3BucketContext();
        try {
            BeanUtils.copyProperties(context, this);
        } catch (Exception e) {
            throw new MojoExecutionException("Error copying properties", e);
        }
        context.setClient(client);
        context.setLastModifiedDateFormatter(getLastModifiedDateFormatter());
        context.setAbout(getAbout());
        return context;
    }

    /**
     * Create a PutObjectRequest for some html generated by this mojo. The PutObjectRequest sets the content type to
     * S3_INDEX_CONTENT_TYPE, sets the ACL to PublicRead, and adds some custom metadata so we can positively identify it
     * as an object created by this plugin
     */
    protected PutObjectRequest getPutIndexObjectRequest(String html, String key) {
        InputStream in = new ByteArrayInputStream(html.getBytes());
        ObjectMetadata om = new ObjectMetadata();
        om.setCacheControl(getCacheControl());
        String contentType = S3_INDEX_CONTENT_TYPE;
        om.setContentType(contentType);
        om.setContentLength(html.length());
        om.addUserMetadata(S3_INDEX_METADATA_KEY, "true");
        PutObjectRequest request = new PutObjectRequest(getBucket(), key, in, om);
        request.setCannedAcl(getAcl());
        return request;
    }

    /**
     * Return a SimpleDateFormat object initialized with the date format and timezone supplied to the mojo
     */
    protected SimpleDateFormat getLastModifiedDateFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat(getDateFormat());
        sdf.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        return sdf;
    }

    /**
     * Return true if the Collection is null or contains no entries, false otherwise
     */
    protected boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

    /**
     * Show some text about this plugin
     */
    protected String getAbout() {
        String date = getLastModifiedDateFormatter().format(new Date());
        PluginDescriptor descriptor = (PluginDescriptor) this.getPluginContext().get("pluginDescriptor");
        if (descriptor == null) {
            // Maven 2.2.1 is returning a null descriptor
            return "Listing generated by the maven-cloudfront-plugin on " + date;
        } else {
            String name = descriptor.getArtifactId();
            String version = descriptor.getVersion();
            return "Listing generated by the " + name + " v" + version + " on " + date;
        }
    }

    public void updateDirectory(UpdateDirectoryContext context) throws IOException {
        updateDirectory(context.getContext(), context.isCopyIfExists(), context.getCopyToKey());
    }

    /**
     * Create an object in the bucket under a key that lets a normal http request function correctly with CloudFront /
     * S3.<br>
     * Either use the client's object or upload some html created by this plugin<br>
     */
    protected void updateDirectory(S3PrefixContext context, boolean isCopyIfExists, String copyToKey)
            throws IOException {
        S3BucketContext bucketContext = context.getBucketContext();
        AmazonS3Client client = context.getBucketContext().getClient();
        String bucket = bucketContext.getBucket();

        boolean containsDefaultObject = isExistingObject(context.getObjectListing(), context.getDefaultObjectKey());
        if (containsDefaultObject && isCopyIfExists) {
            // Copy the contents of the clients default object
            String sourceKey = context.getDefaultObjectKey();
            String destKey = copyToKey;
            CopyObjectRequest request = getCopyObjectRequest(bucket, sourceKey, destKey);
            getLog().debug("Copy: " + sourceKey + " to " + destKey);
            client.copyObject(request);
        } else {
            // Upload our custom content
            PutObjectRequest request = getPutIndexObjectRequest(context.getHtml(), copyToKey);
            getLog().debug("Put: " + copyToKey);
            client.putObject(request);
        }
    }

    /**
     * Update this S3 "directory".
     */
    protected void updateDirectory(final S3PrefixContext context) throws IOException {
        String trimmedPrefix = converter.getTrimmedPrefix(context.getPrefix(), context.getBucketContext()
                .getDelimiter());

        // Handle "http://www.mybucket.com/foo/bar/"
        updateDirectory(context, isCopyDefaultObjectWithDelimiter(), context.getPrefix());

        // Handle "http://www.mybucket.com/foo/bar"
        updateDirectory(context, isCopyDefaultObjectWithoutDelimiter(), trimmedPrefix);

        // Handle "http://www.mybucket.com/foo/bar/browse.html"
        // context.getBucketContext().getClient().putObject(getPutIndexObjectRequest(context.getHtml(),
        // context.getBrowseHtmlKey()));
    }

    /**
     * If this is the root of the bucket and the default object either does not exist or was created by this plugin,
     * overwrite the default object with newly generated html. Otherwise, do nothing.
     */
    public void updateRoot(UpdateDirectoryContext udc) throws IOException {
        updateRoot(udc.getContext());
    }

    /**
     * If this is the root of the bucket and the default object either does not exist or was created by this plugin,
     * overwrite the default object with newly generated html. Otherwise, do nothing.
     */
    protected void updateRoot(S3PrefixContext context) throws IOException {
        AmazonS3Client client = context.getBucketContext().getClient();

        // Handle "http://www.mybucket.com/browse.html"
        PutObjectRequest request1 = getPutIndexObjectRequest(context.getHtml(), context.getBrowseHtmlKey());
        StringBuilder sb = new StringBuilder();
        sb.append(context.getBrowseHtmlKey());
        client.putObject(request1);

        boolean isCreateOrUpdateDefaultObject = isCreateOrUpdateDefaultObject(context);
        if (!isCreateOrUpdateDefaultObject) {
            getLog().debug("Put: " + sb.toString());
            return;
        }

        // Update the default object
        PutObjectRequest request2 = getPutIndexObjectRequest(context.getHtml(), context.getDefaultObjectKey());
        getLog().debug("Put: " + sb.toString() + ", " + context.getDefaultObjectKey());
        client.putObject(request2);
    }

    /**
     * Convert ObjectListing objects into S3PrefixContext objects
     */
    protected List<S3PrefixContext> getS3PrefixContexts(S3BucketContext context, List<ObjectListing> listings) {
        List<S3PrefixContext> contexts = new ArrayList<S3PrefixContext>();
        for (ObjectListing listing : listings) {
            S3PrefixContext prefixContext = getS3PrefixContext(context, listing);
            contexts.add(prefixContext);
        }
        return contexts;
    }

    /**
     * Convert an ObjectListing into an S3PrefixContext
     */
    protected S3PrefixContext getS3PrefixContext(S3BucketContext context, ObjectListing objectListing) {
        String prefix = objectListing.getPrefix();
        String delimiter = context.getDelimiter();
        List<String[]> data = converter.getData(objectListing, prefix, delimiter);
        String html = generator.getHtml(data, prefix, delimiter);
        String defaultObjectKey = StringUtils.isEmpty(prefix) ? getDefaultObjectKey() : prefix + getDefaultObjectKey();
        String browseHtmlKey = StringUtils.isEmpty(prefix) ? getBrowseKey() : prefix + getBrowseKey();
        // Is this the root of the bucket?
        boolean isRoot = StringUtils.isEmpty(prefix);

        S3PrefixContext prefixContext = new S3PrefixContext();
        prefixContext.setObjectListing(objectListing);
        prefixContext.setHtml(html);
        prefixContext.setRoot(isRoot);
        prefixContext.setDefaultObjectKey(defaultObjectKey);
        prefixContext.setPrefix(prefix);
        prefixContext.setBucketContext(context);
        prefixContext.setBrowseHtmlKey(browseHtmlKey);
        return prefixContext;
    }

    /**
     * Return true if the ObjectListing contains an object under "key"
     */
    protected boolean isExistingObject(final ObjectListing objectListing, final String key) {
        List<S3ObjectSummary> summaries = objectListing.getObjectSummaries();
        for (S3ObjectSummary summary : summaries) {
            if (key.equals(summary.getKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if there is no object in the ObjectListing under defaultObjectKey.<br>
     * Return true if the object in the ObjectListing was created by this plugin.<br>
     * Return false otherwise.<br>
     */
    protected boolean isCreateOrUpdateDefaultObject(final S3PrefixContext context) {
        if (!isExistingObject(context.getObjectListing(), context.getDefaultObjectKey())) {
            // There is no default object, we are free to create one
            return true;
        }
        S3BucketContext s3Context = context.getBucketContext();
        // There is a default object, but if it was created by this plugin, we
        // still need to update it
        S3Object s3Object = s3Context.getClient().getObject(s3Context.getBucket(), context.getDefaultObjectKey());
        boolean isOurDefaultObject = isOurObject(s3Object);
        IOUtils.closeQuietly(s3Object.getObjectContent());
        if (isOurDefaultObject) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if this S3Object was created by this plugin. This is is done by checking the metadata attached to
     * this object for the presence of a custom value.
     */
    protected boolean isOurObject(final S3Object s3Object) {
        ObjectMetadata metadata = s3Object.getObjectMetadata();
        Map<String, String> userMetadata = metadata.getUserMetadata();
        String value = userMetadata.get(S3_INDEX_METADATA_KEY);
        boolean isOurObject = "true".equals(value);
        return isOurObject;
    }

    /**
     * Create a CopyObjectRequest with an ACL set to PublicRead
     */
    protected CopyObjectRequest getCopyObjectRequest(final String bucket, final String sourceKey, final String destKey) {
        CopyObjectRequest request = new CopyObjectRequest(bucket, sourceKey, bucket, destKey);
        request.setCannedAccessControlList(getAcl());
        return request;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDefaultObjectKey() {
        return defaultObjectKey;
    }

    public void setDefaultObjectKey(final String defaultCloudFrontObject) {
        this.defaultObjectKey = defaultCloudFrontObject;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(final String fileImage) {
        this.fileImage = fileImage;
    }

    public String getDirectoryImage() {
        return directoryImage;
    }

    public void setDirectoryImage(final String directoryImage) {
        this.directoryImage = directoryImage;
    }

    public String getCss() {
        return css;
    }

    public void setCss(final String css) {
        this.css = css;
    }

    public boolean isCopyDefaultObjectWithDelimiter() {
        return copyDefaultObjectWithDelimiter;
    }

    public void setCopyDefaultObjectWithDelimiter(final boolean copyDefaultObjectWithDelimiter) {
        this.copyDefaultObjectWithDelimiter = copyDefaultObjectWithDelimiter;
    }

    public boolean isCopyDefaultObjectWithoutDelimiter() {
        return copyDefaultObjectWithoutDelimiter;
    }

    public void setCopyDefaultObjectWithoutDelimiter(final boolean copyDefaultObjectWithoutDelimiter) {
        this.copyDefaultObjectWithoutDelimiter = copyDefaultObjectWithoutDelimiter;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(final String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getBrowseKey() {
        return browseKey;
    }

    public void setBrowseKey(final String browseHtml) {
        this.browseKey = browseHtml;
    }

    /**
     * @return the organizationGroupId
     */
    public String getOrganizationGroupId() {
        return organizationGroupId;
    }

    /**
     * @param organizationGroupId
     *            the organizationGroupId to set
     */
    public void setOrganizationGroupId(final String organizationGroupId) {
        this.organizationGroupId = organizationGroupId;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threadCount) {
        this.threads = threadCount;
    }

    public CannedAccessControlList getAcl() {
        return acl;
    }

    public void setAcl(CannedAccessControlList acl) {
        this.acl = acl;
    }

    public List<String> getOrgPomGavs() {
        return orgPomGavs;
    }

    public void setOrgPomGavs(List<String> orgPomGavs) {
        this.orgPomGavs = orgPomGavs;
    }
}
