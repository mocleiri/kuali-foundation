package org.kuali.common.aws.cloudfront;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.s3.BucketContext;

public class CloudFrontContext {

	List<String> welcomeFiles = new ArrayList<String>(CloudFrontConstants.DEFAULT_WELCOME_FILES);
	String cacheControl = CloudFrontConstants.DEFAULT_CACHE_CONTROL;

	ListingConverterService converterService = new DefaultListingConverterService();
	HtmlGeneratorService generatorService = new DefaultHtmlGeneratorService();

	ListingConverterContext converterContext;
	HtmlGeneratorContext generatorContext;

	BucketContext bucketContext;

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
