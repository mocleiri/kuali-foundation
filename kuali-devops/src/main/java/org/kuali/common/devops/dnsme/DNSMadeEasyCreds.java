package org.kuali.common.devops.dnsme;

import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;

public enum DNSMadeEasyCreds {

	// Sandbox credentials expire after 90 days or so
	SANDBOX("cce722ad-25a7-4a6d-a6d7-fceae7c0bc0c", "enc--9jpu7hzzCx7rDbp8qxqH9GGX1bTTCkJuRuaKYWGpVpYV45IMO3SSFwXmNreQ/btT"), //
	PRODUCTION("454f2836-81c0-4379-b8dd-2cc6495131b5", "enc--/Fxj5aXSbOe3AWJ5mAg1M8Lo03+ytqybZuYq3CwUWaZwBnqxEPzpR+/8n19MeRBX");

	private final DNSMadeEasyCredentials.Builder credentials;

	private DNSMadeEasyCreds(String apiKey, String secretKey) {
		this.credentials = DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey);
	}

	public DNSMadeEasyCredentials.Builder getCredentials() {
		return credentials;
	}

}
