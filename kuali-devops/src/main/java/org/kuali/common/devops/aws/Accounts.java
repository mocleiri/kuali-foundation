package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.util.Assert;

public enum Accounts {

	FOUNDATION("foundation", "U2FsdGVkX1/FDB8m4fjGCedORbbrwamkL5froZVyXwI=", "The Kuali Foundation Inc."), //
	STUDENT("student", "U2FsdGVkX1/mw7uS4Rxy9xMoPqYCieZGcCIn34k6yf8=", "Kuali Student"), //
	RICE("rice", "U2FsdGVkX1/oWHrYsZP9jwQOV1PLWY5VE2gg75KV0wE=", "Kuali Rice"), //
	OLE("ole", "U2FsdGVkX19s1I/9y0vHVLzm5VeSHwu0U8qmI2Cjva0=", "Kuali OLE"); //

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
