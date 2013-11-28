package org.kuali.common.aws.auth;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public final class SimpleAWSCredentialsProvider implements AWSCredentialsProvider {

	public SimpleAWSCredentialsProvider(AWSCredentials credentials) {
		Assert.noNulls(credentials);
		this.credentials = credentials;
	}

	private final AWSCredentials credentials;

	@Override
	public AWSCredentials getCredentials() {
		return credentials;
	}

	@Override
	public void refresh() {
		// noop
	}

}
