package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.builder.BuilderContext;
import org.kuali.common.util.builder.BuilderUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.google.common.base.Optional;

public class ImmutableAwsCredentials implements AWSCredentials {

	private final String accessKey;
	private final String secretKey;

	public static class Builder {

		// Required
		private final String accessKey;
		private final String secretKey;

		// Provide a way to override values via system properties / environment variables
		private static final String ACCESS_KEY = "aws.accessKeyId";
		private static final String ACCESS_ENV_KEY = "AWS_ACCESS_KEY_ID";
		private static final String SECRET_KEY = "aws.secretKey";
		private static final String SECRET_ENV_KEY = "AWS_SECRET_KEY";
		private static final Optional<String> ABSENT = Optional.absent();

		/**
		 * Get a set of AWS credentials from system properties / environment variables.
		 */
		public Builder(BuilderContext ctx) {
			this(Optional.of(ctx), ABSENT, ABSENT);
		}

		/**
		 * Create a set of AWS credentials from the string values.
		 */
		public Builder(String accessKey, String secretKey) {
			this(BuilderContext.ABSENT, Optional.of(accessKey), Optional.of(secretKey));
		}

		/**
		 * Use the provided set of AWS credentials unless they are overridden by system properties / environment variables.
		 */
		public Builder(BuilderContext ctx, String accessKey, String secretKey) {
			this(Optional.of(ctx), Optional.of(accessKey), Optional.of(secretKey));
		}

		private Builder(Optional<BuilderContext> ctx, Optional<String> accessKey, Optional<String> secretKey) {
			if (ctx.isPresent()) {
				// Extract and decrypt values from the environment (if they are present)
				// If they are not present, just use the provided values
				this.accessKey = BuilderUtils.getValue(ctx.get(), ACCESS_KEY, ACCESS_ENV_KEY, accessKey);
				this.secretKey = BuilderUtils.getValue(ctx.get(), SECRET_KEY, SECRET_ENV_KEY, secretKey);
			} else {
				this.accessKey = accessKey.get();
				this.secretKey = secretKey.get();
			}
		}

		private void validate(AWSCredentials creds) {
			Assert.noBlanks(creds.getAWSAccessKeyId(), creds.getAWSSecretKey());
			Assert.decrypted(creds.getAWSSecretKey());
			if (creds instanceof AWSSessionCredentials) {
				AWSSessionCredentials sessionCreds = (AWSSessionCredentials) creds;
				Assert.noBlanks(sessionCreds.getSessionToken());
			}
		}

		public AWSCredentials build() {
			AWSCredentials creds = new ImmutableAwsCredentials(this);
			validate(creds);
			return creds;
		}

	}

	private ImmutableAwsCredentials(Builder builder) {
		this.accessKey = builder.accessKey;
		this.secretKey = builder.secretKey;
	}

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}
