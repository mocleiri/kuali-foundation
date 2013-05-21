package org.kuali.common.aws.s3;

public class BucketContext {

	String delimiter = BucketConstants.DEFAULT_DELIMITER;
	Integer maxKeys = BucketConstants.DEFAULT_MAX_KEYS;
	String name;

	public BucketContext() {
		this(null);
	}

	public BucketContext(String name) {
		super();
		this.name = name;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public Integer getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(Integer maxKeys) {
		this.maxKeys = maxKeys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
