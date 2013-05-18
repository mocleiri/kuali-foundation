package org.kuali.common.aws.s3;

import java.util.List;

import org.kuali.common.util.PercentCompleteInformer;

import com.amazonaws.services.s3.AmazonS3Client;

public class BucketContext {

	public static final long DEFAULT_PREFIX_ESTIMATE = 100;
	public static final String DEFAULT_DELIMITER = "/";

	String delimiter = DEFAULT_DELIMITER;
	AmazonS3Client client;
	String bucket;
	String prefix;
	List<String> includes;
	List<String> excludes;
	boolean recursive;
	PercentCompleteInformer informer;

	public AmazonS3Client getClient() {
		return client;
	}

	public void setClient(AmazonS3Client client) {
		this.client = client;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

}
