package org.kuali.common.dns.dnsme.model;

import org.kuali.common.util.Assert;

public final class DNSMadeEasyServiceContext {

	public DNSMadeEasyServiceContext(DNSMadeEasyCredentials credentials, String restApiUrl, String domainName) {
		Assert.noNulls(credentials);
		Assert.noBlanks(restApiUrl, domainName);
		this.credentials = credentials;
		this.restApiUrl = restApiUrl;
		this.domainName = domainName;
	}

	private final DNSMadeEasyCredentials credentials;
	private final String restApiUrl;
	private final String domainName;

	public DNSMadeEasyCredentials getCredentials() {
		return credentials;
	}

	public String getRestApiUrl() {
		return restApiUrl;
	}

	public String getDomainName() {
		return domainName;
	}
}
