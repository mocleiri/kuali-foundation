package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.KeyPair;
import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;

public enum Accounts {

	FOUNDATION("foundation", "3627-3510-8948", Credentials.FOUNDATION, KeyPairs.FOUNDATION.getKeyPair()), //
	STUDENT("student", "0523-3819-3506", Credentials.STUDENT, KeyPairs.STUDENT.getKeyPair()), //
	RICE("rice", "7898-1396-8323", Credentials.RICE, KeyPairs.RICE.getKeyPair()), //
	OLE("ole", "7867-4615-1229", Credentials.OLE, KeyPairs.OLE.getKeyPair()); //

	private final AwsAccount account;

	private Accounts(String name, String accountNumber, AWSCredentials credentials, KeyPair keyPair) {
		Assert.noBlanks(name, accountNumber);
		Assert.noNulls(credentials, keyPair);
		this.account = new AwsAccount.Builder(name, credentials, keyPair).accountNumber(accountNumber).build();
	}

	public AwsAccount getAccount() {
		return account;
	}

	public static Map<String, AwsAccount> asMap() {
		Map<String, AwsAccount> map = new HashMap<String, AwsAccount>();
		for (Accounts accounts : values()) {
			AwsAccount account = accounts.getAccount();
			map.put(account.getName(), account);
		}
		return map;
	}
}
