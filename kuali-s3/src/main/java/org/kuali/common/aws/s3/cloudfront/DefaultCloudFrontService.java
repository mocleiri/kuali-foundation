package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public List<TypedRequest> getIndexObjectRequests(DirectoryContext context, List<ObjectListing> listings) {

		List<TypedRequest> requests = new ArrayList<TypedRequest>();

		// Create s3://bucket/foo
		for (ObjectListing listing : listings) {
			String html = CloudFrontUtils.
			CloudFrontUtils.getPutIndexObjectRequest(bucket, context.getCacheControl(), html, dirNoSlashKey);
			String welcomeFileKey = CloudFrontUtils.getWelcomeFileKey(context.getListing(), context.getWelcomeFiles());
			if (welcomeFileKey == null) {
				// Create s3://bucket/foo/bar/
				CloudFrontUtils.getPutIndexObjectRequest(bucket, context.getCacheControl(), html, dirKey);
			} else {
				// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
				CloudFrontUtils.getCopyObjectRequest(bucket, welcomeFileKey, dirKey);
			}
		}
		return requests;
	}

}
