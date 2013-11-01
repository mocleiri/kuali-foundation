package org.kuali.common.aws.model.util;

import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public class CredentialUtils {

	private static final AWSCredentials NONE = new ImmutableAwsCredentials(NullUtils.NONE, NullUtils.NONE);

	private static final String ACCESS_KEY = "aws.accessKeyId";
	private static final String ACCESS_ENV_KEY = "AWS_ACCESS_KEY_ID";

	private static final String SECRET_KEY = "aws.secretKey";
	private static final String SECRET_ENV_KEY = "AWS_SECRET_KEY";

	public static AWSCredentials getCredentials(EnvironmentService env) {
		return getCredentials(env, NONE);
	}

	public static AWSCredentials getCredentials(EnvironmentService env, AWSCredentials provided) {
		String accessKey = NullUtils.trimToNull(getValue(env, ACCESS_KEY, ACCESS_ENV_KEY));
		String secretKey = NullUtils.trimToNull(getValue(env, SECRET_KEY, SECRET_ENV_KEY));
		return new ImmutableAwsCredentials(accessKey, secretKey);
	}

	protected static String getValue(EnvironmentService env, String key, String envKey) {
		Optional<String> value = SpringUtils.getOptionalString(env, key);
		if (value.isPresent()) {
			return value.get();
		} else {
			return env.getString(envKey);
		}
	}

}
