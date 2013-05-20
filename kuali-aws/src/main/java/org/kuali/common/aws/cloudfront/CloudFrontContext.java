package org.kuali.common.aws.cloudfront;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.s3.BucketContext;

public class CloudFrontContext {

	List<String> welcomeFiles = new ArrayList<String>(CloudFrontConstants.DEFAULT_WELCOME_FILES);
	String cacheControl = CloudFrontConstants.DEFAULT_CACHE_CONTROL;
	BucketContext bucketContext;
	ListingConverterContext converterContext;
	ListingConverterService converterService;
	HtmlGeneratorContext generatorContext;
	HtmlGeneratorService generatorService;

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

	public HtmlGeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	public void setGeneratorContext(HtmlGeneratorContext htmlContext) {
		this.generatorContext = htmlContext;
	}

	public ListingConverterService getConverterService() {
		return converterService;
	}

	public void setConverter(DefaultListingConverterService converter) {
		this.converterService = converter;
	}

	public HtmlGeneratorService getGeneratorService() {
		return generatorService;
	}

	public void setGeneratorService(HtmlGeneratorService generator) {
		this.generatorService = generator;
	}

	public void setConverterService(ListingConverterService converter) {
		this.converterService = converter;
	}

	public ListingConverterContext getConverterContext() {
		return converterContext;
	}

	public void setConverterContext(ListingConverterContext converterContext) {
		this.converterContext = converterContext;
	}

}
