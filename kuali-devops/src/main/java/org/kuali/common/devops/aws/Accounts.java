package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.util.Assert;

public enum Accounts {

	FOUNDATION("foundation", "3627-3510-8948", "The Kuali Foundation Inc."), //
	STUDENT("student", "0523-3819-3506", "Kuali Student"), //
	RICE("rice", "7898-1396-8323", "Kuali Rice"), //
	OLE("ole", "7867-4615-1229", "Kuali OLE"); //

	private final AwsAccount account;

	private Accounts(String name, String accountNumber, String description) {
		Assert.noBlanks(name, accountNumber, description);
		this.account = new AwsAccount.Builder(name, accountNumber).description(description).build();
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
