package org.kuali.common.aws.s3.pojo;

import java.util.List;

public class BucketDeltaSummary {

	String bucket;
	List<BucketDeltaLine> deltaLines;

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public List<BucketDeltaLine> getDeltaLines() {
		return deltaLines;
	}

	public void setDeltaLines(List<BucketDeltaLine> deltaLines) {
		this.deltaLines = deltaLines;
	}

}
