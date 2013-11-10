package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class AwsAccount {

	public AwsAccount(String name, String accountNumber, Optional<String> description) {
		Assert.noBlanks(name, accountNumber);
		Assert.noNulls(description);
		this.name = name;
		this.accountNumber = accountNumber;
		this.description = description;
	}

	private final String name;
	private final String accountNumber;
	private final Optional<String> description;

	public String getName() {
		return name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Optional<String> getDescription() {
		return description;
	}

}
