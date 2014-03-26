package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class IndexObjectsContext {

	CloudFrontContext cloudFrontContext;
	List<ObjectListing> listings;

	public CloudFrontContext getCloudFrontContext() {
		return cloudFrontContext;
	}

	public void setCloudFrontContext(CloudFrontContext cloudFrontContext) {
		this.cloudFrontContext = cloudFrontContext;
	}

	public List<ObjectListing> getListings() {
		return listings;
	}

	public void setListings(List<ObjectListing> listings) {
		this.listings = listings;
	}

}
