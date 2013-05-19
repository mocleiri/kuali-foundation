package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

import org.kuali.common.aws.s3.ObjectListingRequest;

import com.amazonaws.services.s3.model.ObjectListing;

public class DirectoryContext {

	String cacheControl = CloudFrontUtils.DEFAULT_CACHE_CONTROL;
	String delimiter = ObjectListingRequest.DEFAULT_DELIMITER;
	List<String> welcomeFiles;
	String bucket;
	ObjectListing listing;
	String indexHtml;

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

	public String getIndexHtml() {
		return indexHtml;
	}

	public void setIndexHtml(String indexHtml) {
		this.indexHtml = indexHtml;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getCacheControl() {
		return cacheControl;
	}

	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}
