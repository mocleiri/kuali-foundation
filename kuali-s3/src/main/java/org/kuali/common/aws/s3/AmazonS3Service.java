package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface AmazonS3Service {

	List<ObjectListing> getObjectListings(BucketContext context);

}
