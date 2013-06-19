package org.kuali.common.aws.cloudfront;

import java.util.ArrayList;
import java.util.List;

public class CloudFrontContext {

	List<String> welcomeFiles = new ArrayList<String>(CloudFrontConstants.DEFAULT_WELCOME_FILES);
	String cacheControl = CloudFrontConstants.DEFAULT_CACHE_CONTROL;

	public List<String> getWelcomeFiles() {
		return welcomeFiles;
	}

	public void setWelcomeFiles(List<String> welcomeFiles) {
		this.welcomeFiles = welcomeFiles;
	}

	public String getCacheControl() {
		return cacheControl;
	}

	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}

}