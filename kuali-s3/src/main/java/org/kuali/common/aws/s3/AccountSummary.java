package org.kuali.common.aws.s3;

import java.util.List;

public class AccountSummary {

	String accessKey;
	List<BucketSummary> bucketSummaries;
	long size;
	long count;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public List<BucketSummary> getBucketSummaries() {
		return bucketSummaries;
	}

	public void setBucketSummaries(List<BucketSummary> bucketSummaries) {
		this.bucketSummaries = bucketSummaries;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
