package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;

public class IndexDataContext {

	AmazonS3Client client;
	BucketContext bucketContext;
	ListingConverterContext converterContext;
	ObjectListing listing;

	public AmazonS3Client getClient() {
		return client;
	}

	public void setClient(AmazonS3Client client) {
		this.client = client;
	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public ListingConverterContext getConverterContext() {
		return converterContext;
	}

	public void setConverterContext(ListingConverterContext converterContext) {
		this.converterContext = converterContext;
	}

	public ObjectListing getListing() {
		return listing;
	}

	public void setListing(ObjectListing listing) {
		this.listing = listing;
	}

}
