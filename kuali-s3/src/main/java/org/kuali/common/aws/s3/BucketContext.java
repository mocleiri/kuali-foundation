package org.kuali.common.aws.s3;

import com.amazonaws.services.s3.AmazonS3Client;

public class BucketContext {

	public static final String DEFAULT_DELIMITER = "/";

	String delimiter = DEFAULT_DELIMITER;
	AmazonS3Client client;
	String name;

	public BucketContext() {
		this(null, null);
	}

	public BucketContext(AmazonS3Client client, String name) {
		super();
		this.client = client;
		this.name = name;
	}

	public AmazonS3Client getClient() {
		return client;
	}

	public void setClient(AmazonS3Client client) {
		this.client = client;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
