package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ssh.model.KeyPair;

import com.amazonaws.auth.AWSCredentials;

public final class AwsAuth {

	public AwsAuth(AWSCredentials credentials, KeyPair keyPair) {
		Assert.noNulls(credentials, keyPair);
		this.credentials = credentials;
		this.keyPair = keyPair;
	}

	private final AWSCredentials credentials;
	private final KeyPair keyPair;

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

}
