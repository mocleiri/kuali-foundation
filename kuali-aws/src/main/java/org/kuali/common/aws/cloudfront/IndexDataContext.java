package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

import com.amazonaws.services.s3.model.ObjectListing;

public class IndexDataContext {

	BucketContext bucketContext;
	ListingConverterContext converterContext;
	ObjectListing listing;

	public IndexDataContext() {
		this(null, null, null);
	}

	public IndexDataContext(BucketContext bucketContext, ListingConverterContext converterContext, ObjectListing listing) {
		super();
		this.bucketContext = bucketContext;
		this.converterContext = converterContext;
		this.listing = listing;
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
