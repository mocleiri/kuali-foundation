package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public final class DnsmeCredentials {

	public DnsmeCredentials(String apiKey, String secretKey) {
		Assert.noBlanks(apiKey, secretKey);
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}

	private final String apiKey;
	private final String secretKey;

	public String getApiKey() {
		return apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

}
