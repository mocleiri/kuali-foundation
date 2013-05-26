package org.kuali.common.aws.cloudfront;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

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
}
