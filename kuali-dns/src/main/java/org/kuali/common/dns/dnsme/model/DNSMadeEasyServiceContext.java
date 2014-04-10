package org.kuali.common.dns.dnsme.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

public final class DNSMadeEasyServiceContext {

	public DNSMadeEasyServiceContext(DNSMadeEasyCredentials credentials, String restApiUrl, String domainName) {
		this.credentials = checkNotNull(credentials, "credentials");
		this.restApiUrl = checkNotBlank(restApiUrl, "restApiUrl");
		this.domainName = checkNotBlank(domainName, "domainName");
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
