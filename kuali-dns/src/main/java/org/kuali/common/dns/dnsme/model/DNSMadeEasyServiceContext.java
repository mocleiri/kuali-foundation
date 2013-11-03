package org.kuali.common.dns.dnsme.model;

import org.kuali.common.util.Assert;

public final class DNSMadeEasyServiceContext {

	public DNSMadeEasyServiceContext(DNSMadeEasyCredentials credentials, String restApiUrl, String domain) {
		Assert.noNulls(credentials);
		Assert.noBlanks(restApiUrl, domain);
		this.credentials = credentials;
		this.restApiUrl = restApiUrl;
		this.domain = domain;
	}

	private final DNSMadeEasyCredentials credentials;
	private final String restApiUrl;
	private final String domain;

	public DNSMadeEasyCredentials getCredentials() {
		return credentials;
	}

	public String getRestApiUrl() {
		return restApiUrl;
	}

	public String getDomain() {
		return domain;
	}
}
