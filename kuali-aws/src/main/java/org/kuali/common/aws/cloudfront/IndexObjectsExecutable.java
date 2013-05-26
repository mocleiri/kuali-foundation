package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.aws.s3.BucketService;
import org.kuali.common.aws.s3.ListingResult;
import org.kuali.common.aws.s3.ObjectListingsContext;
import org.kuali.common.util.execute.Executable;

public class IndexObjectsExecutable implements Executable {

	CloudFrontContext cloudFrontContext;
	ObjectListingsContext objectListingsContext;
	BucketContext bucketContext;
	BucketService bucketService;
	HtmlGeneratorService generatorService;
	HtmlGeneratorContext generatorContext;
	ListingConverterService converterService;
	ListingConverterContext converterContext;

	@Override
	public void execute() {
		ListingResult listingResult = bucketService.getObjectListings(objectListingsContext);
	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public BucketService getBucketService() {
		return bucketService;
	}

	public void setBucketService(BucketService bucketService) {
		this.bucketService = bucketService;
	}

	public CloudFrontContext getCloudFrontContext() {
		return cloudFrontContext;
	}

	public void setCloudFrontContext(CloudFrontContext cloudFrontContext) {
		this.cloudFrontContext = cloudFrontContext;
	}

	public HtmlGeneratorService getGeneratorService() {
		return generatorService;
	}

	public void setGeneratorService(HtmlGeneratorService generatorService) {
		this.generatorService = generatorService;
	}

	public HtmlGeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	public void setGeneratorContext(HtmlGeneratorContext generatorContext) {
		this.generatorContext = generatorContext;
	}

	public ListingConverterService getConverterService() {
		return converterService;
	}

	public void setConverterService(ListingConverterService converterService) {
		this.converterService = converterService;
	}

	public ListingConverterContext getConverterContext() {
		return converterContext;
	}

	public void setConverterContext(ListingConverterContext converterContext) {
		this.converterContext = converterContext;
	}

}
