package org.kuali.common.dns.dnsme.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

public final class DNSMadeEasyServiceContext {

	public DNSMadeEasyServiceContext(DNSMadeEasyCredentials credentials, String restApiUrl, String domainName) {
		checkNotNull(credentials, "credentials");
		checkNotBlank(restApiUrl, "restApiUrl");
		checkNotBlank(domainName, "domainName");
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
