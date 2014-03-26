package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class IndexContext {

	ObjectListing listing;
	String welcomeFileKey;
	List<String[]> indexData;
	String indexHtml;

	public IndexContext() {
		this(null, null, null);
	}

	public IndexContext(ObjectListing listing) {
		this(listing, null, null);
	}

	public IndexContext(ObjectListing listing, List<String[]> indexData, String welcomeFileKey) {
		super();
		this.listing = listing;
		this.indexData = indexData;
		this.welcomeFileKey = welcomeFileKey;
	}

	public ObjectListing getListing() {
		return listing;
	}

	public void setListing(ObjectListing listing) {
		this.listing = listing;
	}

	public List<String[]> getIndexData() {
		return indexData;
	}

	public void setIndexData(List<String[]> indexData) {
		this.indexData = indexData;
	}

	public String getWelcomeFileKey() {
		return welcomeFileKey;
	}

	public void setWelcomeFileKey(String welcomeFileKey) {
		this.welcomeFileKey = welcomeFileKey;
	}

	public String getIndexHtml() {
		return indexHtml;
	}

	public void setIndexHtml(String indexHtml) {
		this.indexHtml = indexHtml;
	}

}
