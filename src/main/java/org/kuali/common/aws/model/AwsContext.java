package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

public final class AwsContext {

	public AwsContext(AwsAccount account, AwsAuth auth) {
		Assert.noNulls(account, auth);
		this.account = account;
		this.auth = auth;
	}

	private final AwsAccount account;
	private final AwsAuth auth;

	public AwsAccount getAccount() {
		return account;
	}

	public AwsAuth getAuth() {
		return auth;
	}

}
