package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class DirectoryContext {

	ObjectListing listing;
	List<String> welcomeFiles;

	public ObjectListing getListing() {
		return listing;
	}

	public void setListing(ObjectListing listing) {
		this.listing = listing;
	}

	public List<String> getWelcomeFiles() {
		return welcomeFiles;
	}

	public void setWelcomeFiles(List<String> welcomeFiles) {
		this.welcomeFiles = welcomeFiles;
	}

}
