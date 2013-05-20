package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public List<TypedRequest> getIndexObjectRequests(CloudFrontContext context, List<ObjectListing> listings) {

		List<TypedRequest> requests = new ArrayList<TypedRequest>();
		for (ObjectListing listing : listings) {

			String html = "";

			// Create s3://bucket/foo/bar
			TypedRequest request1 = getTypeRequestWithoutTrailingDelimiter(context, listing, html);

			// Create s3://bucket/foo/bar/
			TypedRequest request2 = getTypedRequest(context, listing, html);

			// Add the requests to our list
			requests.add(request1);
			requests.add(request2);

		}
		return requests;
	}

	protected TypedRequest getTypeRequestWithoutTrailingDelimiter(CloudFrontContext context, ObjectListing listing, String html) {
		// Create s3://bucket/foo/bar
		PutObjectRequest index = getPutHtmlRequestWithoutTrailingDelimiter(context, listing, html);
		return new TypedRequest(index, AmazonWebServiceRequestType.PUT_OBJECT);
	}

	/**
	 * This does one of two things. It either copies <code>/foo/bar/index.html to /foo/bar/</code> OR creates <code>/foo/bar/</code> from <code>html</code>
	 */
	protected TypedRequest getTypedRequest(CloudFrontContext context, ObjectListing listing, String html) {
		String welcomeFileKey = CloudFrontUtils.getFirstMatchingKey(listing, context.getWelcomeFiles());
		if (welcomeFileKey == null) {
			// Create s3://bucket/foo/bar/
			PutObjectRequest put = CloudFrontUtils.getPutHtmlRequest(context, html, listing.getPrefix());
			return new TypedRequest(put, AmazonWebServiceRequestType.PUT_OBJECT);
		} else {
			// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
			CopyObjectRequest copy = CloudFrontUtils.getCopyObjectRequest(context, welcomeFileKey, listing.getPrefix());
			return new TypedRequest(copy, AmazonWebServiceRequestType.COPY_OBJECT);
		}
	}

	protected PutObjectRequest getPutHtmlRequestWithoutTrailingDelimiter(CloudFrontContext context, ObjectListing listing, String html) {
		String delimiter = context.getBucketContext().getDelimiter();
		String objectKey = removeSuffix(listing.getPrefix(), delimiter);
		return CloudFrontUtils.getPutHtmlRequest(context, html, objectKey);
	}

	protected PutObjectRequest getPutHtmlRequest(CloudFrontContext context, ObjectListing listing, String html) {
		return CloudFrontUtils.getPutHtmlRequest(context, html, listing.getPrefix());
	}

	protected String removeSuffix(String s, String suffix) {
		Assert.isTrue(StringUtils.endsWith(s, suffix), "[" + s + "] does not end with [" + suffix + "]");
		return StringUtils.substring(s, 0, StringUtils.length(s) - StringUtils.length(suffix));
	}

}
