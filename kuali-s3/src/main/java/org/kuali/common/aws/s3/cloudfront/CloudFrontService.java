package org.kuali.common.aws.s3.cloudfront;

import com.amazonaws.services.s3.model.ObjectListing;

public interface CloudFrontService {

	void ensureBrowsability(ObjectListing listing);

}
