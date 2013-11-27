package org.kuali.common.aws.auth;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public final class DefaultAwsCredentialsService implements AwsCredentialsService {

	public DefaultAwsCredentialsService(AWSCredentialsProvider provider) {
		Assert.noNulls(provider);
		this.provider = provider;
	}

	private final AWSCredentialsProvider provider;

	@Override
	public AWSCredentials getAWSCredentials() {
		return provider.getCredentials();
	}

	public AWSCredentialsProvider getProvider() {
		return provider;
	}

}
