package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class AwsContext {

	public AwsContext(AwsAuth auth, Optional<AwsAccount> account) {
		Assert.noNulls(auth, account);
		this.auth = auth;
		this.account = account;
	}

	private final AwsAuth auth;
	private final Optional<AwsAccount> account;

	public AwsAuth getAuth() {
		return auth;
	}

	public Optional<AwsAccount> getAccount() {
		return account;
	}

}
