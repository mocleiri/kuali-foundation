package org.kuali.common.aws.cloudfront;

import java.util.List;

import org.kuali.common.aws.TypedRequest;

public interface CloudFrontService {

	List<TypedRequest> getIndexObjectRequests(IndexObjectsContext context);

}
