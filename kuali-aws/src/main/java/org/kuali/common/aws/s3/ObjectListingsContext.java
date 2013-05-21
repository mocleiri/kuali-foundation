package org.kuali.common.aws.s3;

import com.amazonaws.services.s3.AmazonS3Client;

public class ObjectListingsContext {

	AmazonS3Client client;
	BucketContext bucketContext;
	ListingRequest request;

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

	public ListingRequest getRequest() {
		return request;
	}

	public void setRequest(ListingRequest request) {
		this.request = request;
	}

}
