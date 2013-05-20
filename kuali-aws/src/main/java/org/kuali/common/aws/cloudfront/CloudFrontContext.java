package org.kuali.common.aws.cloudfront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.aws.s3.BucketContext;

public class CloudFrontContext {

	public static final List<String> DEFAULT_WELCOME_FILES = Arrays.asList("index.html", "welcome.html", "portal.html");

	List<String> welcomeFiles = new ArrayList<String>(DEFAULT_WELCOME_FILES);
	String cacheControl = CloudFrontUtils.DEFAULT_CACHE_CONTROL;
	BucketContext bucketContext;
	ConverterContext converterContext;
	ConverterService converterService;
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

	public ConverterService getConverterService() {
		return converterService;
	}

	public void setConverter(DefaultConverterService converter) {
		this.converterService = converter;
	}

	public HtmlGeneratorService getGeneratorService() {
		return generatorService;
	}

	public void setGeneratorService(HtmlGeneratorService generator) {
		this.generatorService = generator;
	}

	public void setConverterService(ConverterService converter) {
		this.converterService = converter;
	}

	public ConverterContext getConverterContext() {
		return converterContext;
	}

	public void setConverterContext(ConverterContext converterContext) {
		this.converterContext = converterContext;
	}

}
