package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface BucketService {

	/**
	 * Examine an S3 bucket as dictated by <code>context</code>
	 */
	List<ObjectListing> getObjectListings(BucketContext context);

}
