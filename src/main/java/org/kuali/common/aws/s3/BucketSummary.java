package org.kuali.common.aws.s3;

import com.amazonaws.services.s3.model.Bucket;

public class BucketSummary {

	Bucket bucket;
	long count;
	long size;

	public Bucket getBucket() {
		return bucket;
	}

	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
