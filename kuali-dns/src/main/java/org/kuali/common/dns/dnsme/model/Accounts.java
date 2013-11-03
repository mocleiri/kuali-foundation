package org.kuali.common.dns.dnsme.model;

import org.kuali.common.util.Assert;

public enum Accounts {

	// Sandbox credentials expire after 90 days or so
	SANDBOX("cce722ad-25a7-4a6d-a6d7-fceae7c0bc0c", "ENC(9jpu7hzzCx7rDbp8qxqH9GGX1bTTCkJuRuaKYWGpVpYV45IMO3SSFwXmNreQ/btT)"), //
	PRODUCTION("454f2836-81c0-4379-b8dd-2cc6495131b5", "ENC(/Fxj5aXSbOe3AWJ5mAg1M8Lo03+ytqybZuYq3CwUWaZwBnqxEPzpR+/8n19MeRBX)");

	private final DNSMadeEasyCredentials account;

	private Accounts(String apiKey, String secretKey) {
		Assert.noBlanks(apiKey, secretKey);
		this.account = new DNSMadeEasyCredentials(apiKey, secretKey);
	}

	public DNSMadeEasyCredentials getAccount() {
		return account;
	}

}
