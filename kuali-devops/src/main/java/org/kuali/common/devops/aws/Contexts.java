package org.kuali.common.devops.aws;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.AwsAuth;
import org.kuali.common.aws.model.AwsContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.KeyPair;

import com.amazonaws.auth.AWSCredentials;

public enum Contexts {

	FOUNDATION(Accounts.FOUNDATION.getAccount(), Credentials.FOUNDATION, KeyPairs.FOUNDATION.getKeyPair()), //
	STUDENT(Accounts.STUDENT.getAccount(), Credentials.STUDENT, KeyPairs.STUDENT.getKeyPair()), //
	RICE(Accounts.RICE.getAccount(), Credentials.RICE, KeyPairs.RICE.getKeyPair()), //
	OLE(Accounts.OLE.getAccount(), Credentials.OLE, KeyPairs.OLE.getKeyPair());

	private final AwsContext context;

	private Contexts(AwsAccount account, AWSCredentials creds, KeyPair keyPair) {
		Assert.noNulls(account, creds, keyPair);
		AwsAuth auth = new AwsAuth(creds, keyPair);
		this.context = new AwsContext(account, auth);
	}

	public AwsContext getContext() {
		return context;
	}

	public static Map<String, AwsContext> asMap() {
		Map<String, AwsContext> map = new HashMap<String, AwsContext>();
		for (Contexts contexts : values()) {
			String key = contexts.getContext().getAccount().getName();
			map.put(key, contexts.getContext());
		}
		return map;
	}
}
