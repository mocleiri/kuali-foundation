package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public List<TypedRequest> getIndexObjectRequests(CloudFrontContext context, List<ObjectListing> listings) {

		String bucket = context.getBucketContext().getName();
		String delimiter = context.getBucketContext().getDelimiter();

		List<TypedRequest> requests = new ArrayList<TypedRequest>();

		for (ObjectListing listing : listings) {
			String dirKey = listing.getPrefix();
			String dirNoSlashKey = removeTrailingSuffix(dirKey, delimiter);

			PutObjectRequest request = CloudFrontUtils.getPutIndexObjectRequest(bucket, context.getCacheControl(), null, dirNoSlashKey);
			requests.add(new TypedRequest(request,AmazonWebServiceRequestType.PUT));

			String welcomeFileKey = CloudFrontUtils.getWelcomeFileKey(listing, context.getWelcomeFiles());
			if (welcomeFileKey == null) {
				// Create s3://bucket/foo/bar/
				CloudFrontUtils.getPutIndexObjectRequest(bucket, context.getCacheControl(), null, dirKey);
			} else {
				// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
				CloudFrontUtils.getCopyObjectRequest(bucket, welcomeFileKey, dirKey);
			}
		}
		return requests;
	}

	protected String removeTrailingSuffix(String s, String suffix) {
		Assert.isTrue(StringUtils.endsWith(s, suffix), "[" + s + "] does not end with [" + suffix + "]");
		return StringUtils.substring(s, 0, StringUtils.length(s) - StringUtils.length(suffix));
	}

}
