package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface BucketService {

	/**
	 * Return a list of ObjectListings representing the contents of a bucket.
	 */
	List<ObjectListing> getObjectListings(BucketContext context);

}
