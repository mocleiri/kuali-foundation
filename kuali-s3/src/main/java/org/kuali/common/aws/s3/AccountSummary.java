package org.kuali.common.aws.s3;

import java.util.List;

public class AccountSummary {

	String accessKey;
	List<BucketSummary> bucketSummaries;

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

}
