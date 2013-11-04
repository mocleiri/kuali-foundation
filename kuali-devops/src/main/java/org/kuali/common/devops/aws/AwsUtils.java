package org.kuali.common.devops.aws;

import java.util.Map;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.util.CredentialUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public class AwsUtils {

	private static final String ACCOUNT_KEY = "aws.account";
	private static final Map<String, AwsAccount> ACCOUNTS = Accounts.asMap();
	private static final AwsAccount NONE = new AwsAccount.Builder(NullUtils.NONE).build();

	public static AwsAccount getAwsAccount(EnvironmentService env) {
		return getAwsAccount(env, NONE);
	}

	public static AwsAccount getAwsAccount(EnvironmentService env, AwsAccount provided) {
		String accountName = env.getString(ACCOUNT_KEY, provided.getName());
		AwsAccount account = ACCOUNTS.get(accountName);
		Assert.noNulls(account);
		return account;
	}

	public static AWSCredentials getAwsCredentials(EnvironmentService env, EncryptionService enc, AwsAccount provided) {
		Optional<AWSCredentials> credentials = provided.getCredentials();
		if (credentials.isPresent()) {
			return CredentialUtils.getCredentials(env, enc, credentials.get());
		} else {
			return CredentialUtils.getCredentials(env, enc);
		}
	}

}
