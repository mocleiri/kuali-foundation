package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

public class DirectoryListingContext {

	HtmlGeneratorContext generatorContext;
	BucketContext bucketContext;
	IndexContext indexContext;
	String about;

	public HtmlGeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	public void setGeneratorContext(HtmlGeneratorContext generatorContext) {
		this.generatorContext = generatorContext;
	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public IndexContext getIndexContext() {
		return indexContext;
	}

	public void setIndexContext(IndexContext indexContext) {
		this.indexContext = indexContext;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
