package org.kuali.common.aws.s3;

public interface BucketService {

	/**
	 * Examine an S3 bucket and return ObjectListings
	 */
	ObjectListingResult getObjectListings(BucketContext context, ObjectListingRequest request);

}
