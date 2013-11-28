package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSSessionCredentials;

public class ImmutableSessionCredentials implements AWSSessionCredentials {

	public ImmutableSessionCredentials(String accessKey, String secretKey, String sessionToken) {
		Assert.noBlanks(accessKey, secretKey, sessionToken);
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.sessionToken = sessionToken;
	}

	private final String accessKey;
	private final String secretKey;
	private final String sessionToken;

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

	@Override
	public String getSessionToken() {
		return sessionToken;
	}

}
