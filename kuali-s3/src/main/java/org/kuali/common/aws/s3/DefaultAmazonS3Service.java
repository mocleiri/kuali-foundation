package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.aws.s3.cloudfront.CloudFrontHtmlGenerator;
import org.kuali.common.aws.s3.cloudfront.S3BucketContext;
import org.kuali.common.aws.s3.cloudfront.S3DataConverter;
import org.kuali.common.aws.s3.cloudfront.S3PrefixContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultAmazonS3Service implements AmazonS3Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3Service.class);

	@Override
	public DefaultMutableTreeNode getTree(TreeContext context) {
		Assert.notNull(context.getClient(), "client is null");
		Assert.hasText(context.getDelimiter(), "delimiter has no text");
		Assert.hasText(context.getBucket(), "bucket has no text");
		boolean exists = context.getClient().doesBucketExist(context.getBucket());
		Assert.isTrue(exists, "bucket [" + context.getBucket() + "] does not exist");
		Object[] args = { context.getBucket(), context.getDelimiter(), context.getPrefix() };
		logger.info("[s3://{}{}{}] - building tree", args);
		PercentCompleteInformer informer = new PercentCompleteInformer(context.getPrefixCountEstimate());
		informer.start();
		List<ObjectListing> listings = getObjectListings(context, informer);
		informer.stop();
		logger.info("listings: {}", listings.size());
		S3BucketContext sbc = getS3BucketContext(context);
		List<S3PrefixContext> contexts = getS3PrefixContexts(sbc, listings);
		// Convert S3PrefixContext objects into UpdateDirectoryContext objects
		List<UpdateDirectoryContext> udcs = getUpdateDirContexts(sbc, contexts);
		return null;
	}

	/**
	 * Convert S3PrefixContext objects into UpdateDirectoryContext objects. Each S3PrefixContext object generates two S3 calls. One for the prefix with the delimiter and one for
	 * the prefix without the delimiter.
	 * 
	 * @param contexts
	 * @return
	 */
	protected List<UpdateDirectoryContext> getUpdateDirContexts(S3BucketContext sbc, List<S3PrefixContext> contexts) {
		S3DataConverter converter = new S3DataConverter(sbc);
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
			udc1.setCopyIfExists(true);
			udc1.setCopyToKey(context.getPrefix());

			UpdateDirectoryContext udc2 = new UpdateDirectoryContext();
			udc2.setContext(context);
			udc2.setCopyIfExists(false);
			udc2.setCopyToKey(trimmedPrefix);

			list.add(udc1);
			list.add(udc2);

		}
		return list;
	}

	/**
	 * Recurse the bucket starting at <code>prefix</code> acquiring an <code>ObjectListing</code> for each prefix along the way.
	 */
	protected List<ObjectListing> getObjectListings(TreeContext context, PercentCompleteInformer informer) {
		AmazonS3Client client = context.getClient();
		String prefix = getPrefix(context.getPrefix(), context.getDelimiter());
		ListObjectsRequest request = getListObjectsRequest(context.getBucket(), prefix, context.getDelimiter(), null);
		ObjectListing listing = client.listObjects(request);
		informer.incrementProgress();
		List<String> commonPrefixes = listing.getCommonPrefixes();
		List<ObjectListing> listings = new ArrayList<ObjectListing>();
		listings.add(listing);
		for (String commonPrefix : commonPrefixes) {
			if (include(context, commonPrefix)) {
				TreeContext clone = clone(context, commonPrefix);
				List<ObjectListing> children = getObjectListings(clone, informer);
				listings.addAll(children);
			}
		}
		return listings;
	}

	protected void log(String prefix, long count, long skipped, long requests) {
		int padding = 10;
		String r = StringUtils.leftPad(FormatUtils.getCount(requests), padding);
		String t = StringUtils.leftPad(FormatUtils.getCount(count + skipped), padding);
		String c = StringUtils.leftPad(FormatUtils.getCount(count), padding);
		String s = StringUtils.leftPad(FormatUtils.getCount(skipped), padding);
		Object[] args = { r, t, c, s, prefix };
		logger.debug("{} {} {} {} - {}", args);
	}

	protected String getPrefix(String prefix, String delimiter) {
		if (StringUtils.isBlank(prefix) || StringUtils.equals(prefix, delimiter)) {
			return null;
		} else {
			return StringUtils.endsWith(prefix, delimiter) ? prefix : prefix + delimiter;
		}
	}

	protected String getSuffixPattern(String pattern, String delimiter) {
		Assert.hasText(pattern, "pattern has no text");
		Assert.hasText(delimiter, "delimiter has no text");
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.startsWith(pattern, delimiter)) {
			sb.append(delimiter);
		}
		sb.append(pattern);
		if (!StringUtils.endsWith(pattern, delimiter)) {
			sb.append(delimiter);
		}
		return sb.toString();
	}

	protected boolean isMatch(String prefix, String pattern, String delimiter) {
		String suffix = getSuffixPattern(pattern, delimiter);
		return StringUtils.endsWith(prefix, suffix);
	}

	protected boolean isExclude(TreeContext context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getExcludes())) {
			if (isMatch(prefix, exclude, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(TreeContext context, String prefix) {
		if (CollectionUtils.isEmpty(context.getIncludes())) {
			return true;
		}
		for (String include : context.getIncludes()) {
			if (isMatch(prefix, include, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean include(TreeContext context, String prefix) {
		return !isExclude(context, prefix) && isInclude(context, prefix);
	}

	protected TreeContext clone(TreeContext context, String prefix) {
		TreeContext clone = new TreeContext();
		try {
			BeanUtils.copyProperties(clone, context);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	@Override
	public Bucket getBucket(AmazonS3Client client, String bucketName) {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (StringUtils.equals(bucketName, bucket.getName())) {
				return bucket;
			}
		}
		return null;
	}

	protected ListObjectsRequest getListObjectsRequest(String bucket, String prefix, String delimiter, Integer maxKeys) {
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(bucket);
		request.setDelimiter(delimiter);
		request.setPrefix(prefix);
		request.setMaxKeys(maxKeys);
		return request;
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

		// TODO Move this out to somewhere else
		CloudFrontHtmlGenerator generator = new CloudFrontHtmlGenerator(context);
		S3DataConverter converter = new S3DataConverter(context);
		converter.setBrowseKey(S3PrefixContext.BROWSE_HTML);

		String prefix = objectListing.getPrefix();
		String delimiter = context.getDelimiter();
		List<String[]> data = converter.getData(objectListing, prefix, delimiter);
		String html = generator.getHtml(data, prefix, delimiter);
		String defaultObjectKey = StringUtils.isEmpty(prefix) ? S3PrefixContext.INDEX_HTML : prefix + S3PrefixContext.INDEX_HTML;
		String browseHtmlKey = StringUtils.isEmpty(prefix) ? S3PrefixContext.BROWSE_HTML : prefix + S3PrefixContext.BROWSE_HTML;
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
	 * Get context information about the bucket we are operating on
	 */
	protected S3BucketContext getS3BucketContext(TreeContext context) {
		S3BucketContext sbc = new S3BucketContext();
		sbc.setClient(context.getClient());
		sbc.setBucket(context.getBucket());
		sbc.setAbout("Created by maven-cloudfront-plugin on " + sbc.getLastModifiedDateFormatter().format(new Date()));
		return sbc;
	}
}
