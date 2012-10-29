package org.kuali.common.aws.s3;

import java.util.List;

public class AccountDeltaSummary {

	String accessKey;
	List<BucketDeltaSummary> bucketDeltaSummaries;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public List<BucketDeltaSummary> getBucketDeltaSummaries() {
		return bucketDeltaSummaries;
	}

	public void setBucketDeltaSummaries(List<BucketDeltaSummary> bucketDeltaSummaries) {
		this.bucketDeltaSummaries = bucketDeltaSummaries;
	}

}
