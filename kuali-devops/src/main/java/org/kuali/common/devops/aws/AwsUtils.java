package org.kuali.common.devops.aws;

import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;

public class AwsUtils {

	private static final String ACCOUNT_KEY = "aws.account";
	private static final Map<String, AwsAccount> ACCOUNTS = Accounts.asMap();
	private static final KeyPair NOKEYPAIR = new KeyPair(NullUtils.NONE, NullUtils.NONE);
	private static final AWSCredentials NOCREDS = new ImmutableAwsCredentials(NullUtils.NONE, NullUtils.NONE);
	private static final AwsAccount NOACCOUNT = new AwsAccount.Builder(NullUtils.NONE, NOCREDS, NOKEYPAIR).build();

	public static AwsAccount getAwsAccount(EnvironmentService env) {
		return getAwsAccount(env, NOACCOUNT);
	}

	public static AwsAccount getAwsAccount(EnvironmentService env, AwsAccount provided) {
		String accountName = NullUtils.trimToNull(env.getString(ACCOUNT_KEY, provided.getName()));
		Assert.noBlanks(accountName);
		AwsAccount account = ACCOUNTS.get(accountName);
		Assert.noNulls(account);
		return account;
	}

}
