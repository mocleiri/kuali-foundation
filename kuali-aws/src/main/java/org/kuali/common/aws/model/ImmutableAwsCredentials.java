package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;

public class ImmutableAwsCredentials implements AWSCredentials {

	public ImmutableAwsCredentials(String accessKey, String secretKey) {
		Assert.noBlanks(accessKey, secretKey);
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	public ImmutableAwsCredentials(AWSCredentials credentials) {
		this(credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey());
	}

	private final String accessKey;
	private final String secretKey;

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}
