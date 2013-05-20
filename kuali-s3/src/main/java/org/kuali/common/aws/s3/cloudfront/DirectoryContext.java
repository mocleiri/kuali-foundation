package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

import org.kuali.common.aws.s3.ObjectListingRequest;

public class DirectoryContext {

	String cacheControl = CloudFrontUtils.DEFAULT_CACHE_CONTROL;
	String delimiter = ObjectListingRequest.DEFAULT_DELIMITER;
	List<String> welcomeFiles;
	String bucket;

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

	public List<String> getWelcomeFiles() {
		return welcomeFiles;
	}

	public void setWelcomeFiles(List<String> welcomeFiles) {
		this.welcomeFiles = welcomeFiles;
	}

}
