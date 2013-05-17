package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface AmazonS3Service {

	/**
	 * Recursively examine a bucket starting at <code>prefix</code>. The list returned by this method contains an object listing for every "directory" and "sub-directory" rooted at
	 * <code>prefix</code>
	 */
	List<ObjectListing> getObjectListings(BucketContext context);

}
