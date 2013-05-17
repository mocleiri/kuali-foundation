package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface AmazonS3Service {

	/**
	 * Recursively examine a bucket starting at <code>prefix</code>. The list returned by this method will contain
	 */
	List<ObjectListing> getObjectListings(BucketContext context);

}
