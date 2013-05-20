package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class IndexContext {

	ObjectListing listing;
	List<String[]> indexData;
	String indexHtml;
	String welcomeFileKey;

	public IndexContext() {
		this(null, null);
	}

	public IndexContext(ObjectListing listing, List<String[]> indexData) {
		super();
		this.listing = listing;
		this.indexData = indexData;
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

	public String getIndexHtml() {
		return indexHtml;
	}

	public void setIndexHtml(String indexHtml) {
		this.indexHtml = indexHtml;
	}

	public String getWelcomeFileKey() {
		return welcomeFileKey;
	}

	public void setWelcomeFileKey(String welcomeFileKey) {
		this.welcomeFileKey = welcomeFileKey;
	}

}
