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
	private static final CannedAccessControlList DEFAULT_ACL = CannedAccessControlList.PublicRead;
	private static final String S3_INDEX_METADATA_KEY = "maven-cloudfront-plugin-index";
	private static final String S3_INDEX_CONTENT_TYPE = "text/html";

	/**
	 * An <code>ObjectListing</code> is the equivalent of typing <code>ls</code> in a directory on a file system.
	 */
	public static String getWelcomeFileKey(ObjectListing listing, List<String> welcomeFiles) {
		// Cycle through the list of files in this directory
		for (S3ObjectSummary summary : listing.getObjectSummaries()) {
			String welcomeFileKey = getWelcomeFileKey(listing, summary, welcomeFiles);
			if (welcomeFileKey != null) {
				return welcomeFileKey;
			}
		}
		return null;
	}

	protected static String getWelcomeFileKey(ObjectListing listing, S3ObjectSummary summary, List<String> welcomeFiles) {
		// Cycle through the list of welcome files
		for (String welcomeFile : welcomeFiles) {
			// Append the welcome file name to the key for this directory
			String welcomeFileKey = listing.getPrefix() + welcomeFile;
			// We found a welcome file for this directory
			if (StringUtils.equals(summary.getKey(), welcomeFileKey)) {
				return welcomeFileKey;
			}
		}
		return null;
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
	public static PutObjectRequest getPutIndexObjectRequest(String bucket, String cacheControl, String html, String key) {

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
