package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.util.Assert;

public enum Accounts {

	FOUNDATION("foundation", "LT/L6vhenEvUms15oHjSTjiohLNdAOxk", "The Kuali Foundation Inc."), //
	STUDENT("student", "SOnZSbgoOxeC7c0UhnzeEexICurcQm3M", "Kuali Student"), //
	RICE("rice", "EOePxVxBqQJeoHakLYP7EmxUiWu8/2qq", "Kuali Rice"), //
	OLE("ole", "pbqNKsLTtIdJer7k+8WAzpbQk8wsL6bH", "Kuali OLE"); //

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
