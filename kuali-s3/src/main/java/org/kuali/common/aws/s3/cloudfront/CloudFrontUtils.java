package org.kuali.common.aws.s3.cloudfront;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class CloudFrontUtils {

	public static final String DEFAULT_CACHE_CONTROL = "max-age=3600, must-revalidate";
	public static final CannedAccessControlList DEFAULT_ACL = CannedAccessControlList.PublicRead;

	private static final String S3_INDEX_METADATA_KEY = "maven-cloudfront-plugin-index";
	private static final String S3_INDEX_CONTENT_TYPE = "text/html";

	/**
	 * An <code>ObjectListing</code> is the equivalent of typing <code>ls</code> in a directory on a file system.
	 */
	public static String getFirstMatchingKey(ObjectListing listing, List<String> filenames) {
		// Cycle through the list of files in this directory
		for (S3ObjectSummary summary : listing.getObjectSummaries()) {
			String objectKey = getFirstMatchingKey(listing, summary, filenames);
			if (objectKey != null) {
				return objectKey;
			}
		}
		return null;
	}

	public static String getFirstMatchingKey(ObjectListing listing, S3ObjectSummary summary, List<String> filenames) {
		// Cycle through the list of filenames
		for (String filename : filenames) {
			// Append the file name to the key for this directory
			String objectKey = listing.getPrefix() + filename;
			// We found a filename in this directory that matches what we are looking for
			if (StringUtils.equals(summary.getKey(), objectKey)) {
				return objectKey;
			}
		}
		return null;
	}

	/**
	 * Create a CopyObjectRequest with an ACL set to PublicRead
	 */
	public static CopyObjectRequest getCopyObjectRequest(CloudFrontContext context, String src, String dst) {
		return getCopyObjectRequest(context.getBucketContext().getName(), src, dst);
	}

	/**
	 * Create a CopyObjectRequest with an ACL set to PublicRead
	 */
	public static CopyObjectRequest getCopyObjectRequest(String bucket, String src, String dst) {
		CopyObjectRequest request = new CopyObjectRequest(bucket, src, bucket, dst);
		request.setCannedAccessControlList(DEFAULT_ACL);
		return request;
	}

	/**
	 * Create a PutObjectRequest from the html. The PutObjectRequest sets the content type to <code>text/html</code>, sets the ACL to <code>PublicRead</code>, and adds the metadata
	 * <code>maven-cloudfront-plugin-index=true</code>
	 */
	public static PutObjectRequest getPutHtmlRequest(CloudFrontContext context, String html, String key) {
		String bucket = context.getBucketContext().getName();
		String cacheControl = context.getCacheControl();
		return getPutHtmlRequest(bucket, cacheControl, html, key);

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
		om.setContentType(S3_INDEX_CONTENT_TYPE);
		om.setContentLength(html.length());
		om.addUserMetadata(S3_INDEX_METADATA_KEY, "true");

		// Create a request object
		PutObjectRequest request = new PutObjectRequest(bucket, key, in, om);
		request.setCannedAcl(DEFAULT_ACL);
		return request;
	}
}
