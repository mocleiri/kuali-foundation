package org.kuali.common.dns.dnsme.model;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public enum Accounts {

	SANDBOX("", ""), PRODUCTION("", "");

	private final Account account;

	private Accounts(String apiKey, String secretKey) {
		Assert.noBlanks(apiKey, secretKey);
		this.account = new Account(apiKey, secretKey, ImmutableList.<Domain> of());
	}

	public Account getAccount() {
		return account;
	}

}
