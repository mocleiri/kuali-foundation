package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.AwsKey;
import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;

public enum Accounts {

	FOUNDATION("foundation", "3627-3510-8948", Credentials.FOUNDATION, Keys.FOUNDATION.getKey()), //
	KS("ks", "0523-3819-3506", Credentials.KS, Keys.KS.getKey()), //
	RICE("rice", "7898-1396-8323", Credentials.RICE, Keys.RICE.getKey()), //
	OLE("ole", "7867-4615-1229", Credentials.OLE, Keys.OLE.getKey()); //

	private final AwsAccount account;

	private Accounts(String name, String accountNumber, AWSCredentials credentials, AwsKey key) {
		Assert.noBlanks(name, accountNumber);
		Assert.noNulls(credentials, key);
		this.account = new AwsAccount.Builder(name, accountNumber).credentials(credentials).key(key).build();
	}

	public AwsAccount getAccount() {
		return account;
	}

	public static Map<String, AwsAccount> getAccounts() {
		Map<String, AwsAccount> map = new HashMap<String, AwsAccount>();
		for (Accounts accounts : values()) {
			AwsAccount account = accounts.getAccount();
			map.put(account.getName(), account);
		}
		return map;
	}
}
