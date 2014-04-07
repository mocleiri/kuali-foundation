/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.cloudfront;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.aws.AmazonWebServiceRequestType;
import org.kuali.common.aws.TypedRequest;
import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.util.Str;

import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class CloudFrontUtils {

	private static final String INDEX_METADATA_KEY = "maven-cloudfront-plugin-index";
	private static final String INDEX_CONTENT_TYPE = "text/html";

	/**
	 * Return a date formatter for the indicated format and timezone.
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format, String timezone) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf;
	}

	/**
	 * An <code>ObjectListing</code> is the equivalent of typing <code>ls</code> in a directory on a file system.
	 */
	public static String getFirstMatchingKey(ObjectListing listing, List<String> filenames) {
		// Cycle through the list of files in this directory
		for (S3ObjectSummary summary : listing.getObjectSummaries()) {
			String objectKey = getFirstMatchingKey(summary.getKey(), listing.getPrefix(), filenames);
			if (objectKey != null) {
				return objectKey;
			}
		}
		return null;
	}

	public static String getFirstMatchingKey(String objectKey, String prefix, List<String> filenames) {
		// Cycle through the list of filenames
		for (String filename : filenames) {
			// Append the file name to the key for this directory
			String completeKey = prefix + filename;
			// We found a filename in this directory that matches what we are looking for
			if (StringUtils.equals(objectKey, completeKey)) {
				return completeKey;
			}
		}
		return null;
	}

	/**
	 * Create a CopyObjectRequest with an ACL set to PublicRead
	 */
	public static CopyObjectRequest getCopyObjectRequest(String bucket, String src, String dst) {
		CopyObjectRequest request = new CopyObjectRequest(bucket, src, bucket, dst);
		request.setCannedAccessControlList(CloudFrontConstants.DEFAULT_ACL);
		return request;
	}

	/**
	 * Create a PutObjectRequest from the html. The PutObjectRequest sets the content type to <code>text/html</code>, sets the ACL to <code>PublicRead</code>, and adds the metadata
	 * <code>maven-cloudfront-plugin-index=true</code>
	 */
	public static PutObjectRequest getPutHtmlRequest(String bucket, String cacheControl, String html, String key) {

		// Setup an InputStream that reads from the HTML string
		InputStream in = new ByteArrayInputStream(html.getBytes());

		// Create some metadata for identifying this S3 object as an index
		ObjectMetadata om = new ObjectMetadata();
		om.setCacheControl(cacheControl);
		om.setContentType(INDEX_CONTENT_TYPE);
		om.setContentLength(html.length());
		om.addUserMetadata(INDEX_METADATA_KEY, "true");

		// Create a request object
		PutObjectRequest request = new PutObjectRequest(bucket, key, in, om);
		request.setCannedAcl(CloudFrontConstants.DEFAULT_ACL);
		return request;
	}

	public static TypedRequest getTypedRequestWithoutTrailingDelimiter(BucketContext context, String cacheControl, ObjectListing listing, String html) {
		// Create s3://bucket/foo/bar
		PutObjectRequest index = getPutHtmlRequestWithoutTrailingDelimiter(context, cacheControl, listing, html);
		return new TypedRequest(index, AmazonWebServiceRequestType.PUT_OBJECT);
	}

	/**
	 * This does one of two things. It either copies <code>/foo/bar/index.html to /foo/bar/</code> OR creates <code>/foo/bar/</code> from <code>html</code>
	 */
	public static TypedRequest getTypedRequest(String bucket, String cacheControl, String welcomeFileKey, ObjectListing listing, String html) {
		if (welcomeFileKey == null) {
			// Create s3://bucket/foo/bar/
			PutObjectRequest put = getPutHtmlRequest(bucket, cacheControl, html, listing.getPrefix());
			return new TypedRequest(put, AmazonWebServiceRequestType.PUT_OBJECT);
		} else {
			// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
			CopyObjectRequest copy = getCopyObjectRequest(bucket, welcomeFileKey, listing.getPrefix());
			return new TypedRequest(copy, AmazonWebServiceRequestType.COPY_OBJECT);
		}
	}

	public static PutObjectRequest getPutHtmlRequestWithoutTrailingDelimiter(BucketContext context, String cacheControl, ObjectListing listing, String html) {
		String delimiter = context.getDelimiter();
		String objectKey = Str.removeSuffix(listing.getPrefix(), delimiter);
		return getPutHtmlRequest(context.getName(), cacheControl, html, objectKey);
	}

	public static PutObjectRequest getPutHtmlRequest(BucketContext context, String cacheControl, ObjectListing listing, String html) {
		return getPutHtmlRequest(context.getName(), cacheControl, html, listing.getPrefix());
	}
}
