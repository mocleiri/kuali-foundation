package org.kuali.common.aws.cloudfront;

import java.util.List;

import org.kuali.common.aws.TypedRequest;

import com.amazonaws.services.s3.model.ObjectListing;

public interface CloudFrontService {

	List<TypedRequest> getIndexObjectRequests(CloudFrontContext context, List<ObjectListing> listings);

}
