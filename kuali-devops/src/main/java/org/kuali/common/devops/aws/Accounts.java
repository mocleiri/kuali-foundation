package org.kuali.common.devops.aws;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.AwsKey;
import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;

public enum Accounts {

	FOUNDATION("foundation", "", Credentials.FOUNDATION, Keys.FOUNDATION.getKey()), //
	KS("ks", "", Credentials.KS, Keys.KS.getKey()), //
	RICE("rice", "", Credentials.RICE, Keys.RICE.getKey()), //
	OLE("ole", "", Credentials.OLE, Keys.OLE.getKey()); //

	private final AwsAccount account;

	private Accounts(String name, String accountNumber, AWSCredentials credentials, AwsKey key) {
		Assert.noBlanks(name, accountNumber);
		Assert.noNulls(credentials, key);
		this.account = new AwsAccount.Builder(name, accountNumber).credentials(credentials).key(key).build();
	}

	public AwsAccount getAccount() {
		return account;
	}
}
