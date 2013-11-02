package org.kuali.common.dns.dnsme.model;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public enum Accounts {

	SANDBOX("549de3da-8eae-4350-b20e-3d5c31f7117e", "ENC(tOH/wJCMfs8WrgL2TR+6zIDqvvmXvQpaN7td5WohonWW1mp5XoN+1LmZowNIn7X9)"), //
	PRODUCTION("454f2836-81c0-4379-b8dd-2cc6495131b5", "ENC(/Fxj5aXSbOe3AWJ5mAg1M8Lo03+ytqybZuYq3CwUWaZwBnqxEPzpR+/8n19MeRBX)");

	private final Account account;

	private Accounts(String apiKey, String secretKey) {
		Assert.noBlanks(apiKey, secretKey);
		this.account = new Account(apiKey, secretKey, ImmutableList.<Domain> of());
	}

	public Account getAccount() {
		return account;
	}

}
