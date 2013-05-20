package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface CloudFrontService {

	List<TypedRequest> getIndexObjectRequests(DirectoryContext context, List<ObjectListing> listings);

}
