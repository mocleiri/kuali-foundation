package org.kuali.common.aws.s3;

import java.util.Date;

public class BucketSummaryLine {

	String bucket;
	long files;
	long bytes;
	Date date;

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public long getFiles() {
		return files;
	}

	public void setFiles(long files) {
		this.files = files;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long size) {
		this.bytes = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
