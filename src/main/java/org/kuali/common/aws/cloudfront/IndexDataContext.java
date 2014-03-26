package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

public class IndexDataContext {

	BucketContext bucketContext;
	ListingConverterContext converterContext;

	public IndexDataContext() {
		this(null, null);
	}

	public IndexDataContext(BucketContext bucketContext, ListingConverterContext converterContext) {
		super();
		this.bucketContext = bucketContext;
		this.converterContext = converterContext;
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

}
