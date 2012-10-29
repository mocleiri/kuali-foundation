package org.kuali.common.aws.s3.pojo;

import java.util.Date;

public class BucketDeltaLine {

	String bucket;
	long fileDelta;
	long byteDelta;
	Date startDate;
	Date endDate;
	long interval;

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public long getFileDelta() {
		return fileDelta;
	}

	public void setFileDelta(long fileDelta) {
		this.fileDelta = fileDelta;
	}

	public long getByteDelta() {
		return byteDelta;
	}

	public void setByteDelta(long byteDelta) {
		this.byteDelta = byteDelta;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}
