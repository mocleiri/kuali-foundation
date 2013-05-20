package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.aws.s3.BucketContext;

public class CloudFrontContext {

	public static final List<String> DEFAULT_WELCOME_FILES = Arrays.asList("index.html", "welcome.html", "portal.html");

	List<String> welcomeFiles = new ArrayList<String>(DEFAULT_WELCOME_FILES);
	String cacheControl = CloudFrontUtils.DEFAULT_CACHE_CONTROL;
	BucketContext bucketContext;
	HtmlContext htmlContext;
	ObjectListingConverter converter;

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

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

	public HtmlContext getHtmlContext() {
		return htmlContext;
	}

	public void setHtmlContext(HtmlContext htmlContext) {
		this.htmlContext = htmlContext;
	}

	public ObjectListingConverter getConverter() {
		return converter;
	}

	public void setConverter(ObjectListingConverter converter) {
		this.converter = converter;
	}

}
