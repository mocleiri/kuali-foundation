package org.kuali.common.aws.model.util;

import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
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

	public static AWSCredentials getCredentials(EnvironmentService env, EncryptionService enc) {
		return getCredentials(env, enc, NONE);
	}

	public static AWSCredentials getCredentials(EnvironmentService env, EncryptionService enc, AWSCredentials provided) {
		String accessKey = NullUtils.trimToNull(getValue(env, ACCESS_KEY, ACCESS_ENV_KEY, provided.getAWSAccessKeyId()));
		String rawSecretKey = NullUtils.trimToNull(getValue(env, SECRET_KEY, SECRET_ENV_KEY, provided.getAWSSecretKey()));
		Assert.noBlanks(accessKey, rawSecretKey);
		String secretKey = EncUtils.isEncrypted(rawSecretKey) ? enc.decrypt(rawSecretKey) : rawSecretKey;
		return new ImmutableAwsCredentials(accessKey, secretKey);
	}

	protected static String getValue(EnvironmentService env, String key, String envKey, String provided) {
		Optional<String> value = SpringUtils.getOptionalString(env, key);
		Optional<String> envValue = SpringUtils.getOptionalString(env, envKey);
		if (value.isPresent()) {
			return value.get();
		} else if (envValue.isPresent()) {
			return envValue.get();
		} else {
			return provided;
		}
	}

}
