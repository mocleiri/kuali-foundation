package org.kuali.common.devops.aws;

import java.util.Map;

import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.AwsAuth;
import org.kuali.common.aws.model.AwsContext;
import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.aws.model.util.CredentialUtils;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public class AwsUtils {

	private static final String NAME_KEY = "aws.account";
	private static final String ACCOUNT_NUMBER_KEY = "aws.accountNumber";
	private static final String ACCOUNT_DESCRIPTION_KEY = "aws.accountDescription";

	private static final String NONE = NullUtils.NONE;

	private static final Map<String, AwsContext> CONTEXTS = Contexts.asMap();
	private static final KeyPair NOKEYPAIR = new KeyPair.Builder(NONE).build();
	private static final AWSCredentials NOCREDS = new ImmutableAwsCredentials(NONE, NONE);
	private static final AwsAuth NOAUTH = new AwsAuth(NOCREDS, NOKEYPAIR);
	private static final AwsAccount UNKNOWN_ACCOUNT = new AwsAccount(NONE, NONE, Optional.<String> absent());
	private static final AwsContext UNKNOWN_CONTEXT = new AwsContext(UNKNOWN_ACCOUNT, NOAUTH);

	public static AwsContext getAwsContext(EnvironmentService env, EncryptionService enc) {
		return getAwsContext(env, enc, UNKNOWN_CONTEXT);
	}

	public static AwsAccount getAwsAccount(EnvironmentService env, AwsAccount provided) {
		String name = env.getString(NAME_KEY, provided.getName());
		String accountNumber = env.getString(ACCOUNT_NUMBER_KEY, provided.getAccountNumber());
		Optional<String> description = SpringUtils.getString(env, ACCOUNT_DESCRIPTION_KEY, provided.getDescription());
		return new AwsAccount(name, accountNumber, description);
	}

	public static AwsContext getAwsContext(EnvironmentService env, EncryptionService enc, AwsContext provided) {
		String name = env.getString(NAME_KEY, provided.getAccount().getName());
		AwsContext context = getInternalContext(name, provided);
		AwsAccount account = getAwsAccount(env, context.getAccount());
		AWSCredentials credentials = CredentialUtils.getCredentials(env, enc, context.getAuth().getCredentials());
		KeyPair keyPair = getKeyPair(env, enc, context.getAuth().getKeyPair());
		AwsAuth auth = new AwsAuth(credentials, keyPair);
		return new AwsContext(account, auth);
	}

	public static AwsContext getInternalContext(String name, AwsContext provided) {
		AwsContext internal = CONTEXTS.get(name);
		if (internal != null) {
			return internal;
		} else {
			return provided;
		}
	}

	/**
	 * Get a decrypted key pair based on the values from <code>provided</code> unless overridden by values from the environment.
	 */
	public static KeyPair getKeyPair(EnvironmentService env, EncryptionService enc, KeyPair provided) {
		KeyPair keyPair = LaunchUtils.getKeyPair(env, provided);
		return EncUtils.decrypt(enc, keyPair);
	}

}
