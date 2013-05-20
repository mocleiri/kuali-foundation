package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface CloudFrontService {

	List<TypedRequest> getIndexObjectRequests(CloudFrontContext context, List<ObjectListing> listings);

}
