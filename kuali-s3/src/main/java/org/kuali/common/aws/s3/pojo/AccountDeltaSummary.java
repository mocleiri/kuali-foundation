package org.kuali.common.aws.s3.pojo;

import java.util.List;

public class AccountDeltaSummary {

	List<BucketDeltaSummary> bucketDeltaSummaries;

	public List<BucketDeltaSummary> getBucketDeltaSummaries() {
		return bucketDeltaSummaries;
	}

	public void setBucketDeltaSummaries(List<BucketDeltaSummary> bucketDeltaSummaries) {
		this.bucketDeltaSummaries = bucketDeltaSummaries;
	}

}
