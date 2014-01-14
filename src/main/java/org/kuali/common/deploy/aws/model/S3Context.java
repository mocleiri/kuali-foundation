package org.kuali.common.deploy.aws.model;

import org.kuali.common.util.Assert;

public final class S3Context {

	public S3Context(String accessKey, String secretKey) {
		Assert.noBlanks(accessKey, secretKey);
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	private final String accessKey;
	private final String secretKey;

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

}
