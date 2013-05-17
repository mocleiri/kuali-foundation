package org.kuali.common.aws.s3;

import com.amazonaws.services.s3.model.ObjectListing;

public class BucketPrefixContext {

	ObjectListing listing;
	String welcomeFileKey;

	public ObjectListing getListing() {
		return listing;
	}

	public void setListing(ObjectListing listing) {
		this.listing = listing;
	}

	public String getWelcomeFileKey() {
		return welcomeFileKey;
	}

	public void setWelcomeFileKey(String welcomeFileKey) {
		this.welcomeFileKey = welcomeFileKey;
	}

}
