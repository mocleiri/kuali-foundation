package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.builder.BuilderContext;
import org.kuali.common.util.builder.BuilderUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.auth.AWSCredentials;
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

		public Builder(AWSCredentials credentials) {
			this(BuilderContext.ABSENT, credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey());
		}

		public Builder(BuilderContext ctx, AWSCredentials credentials) {
			this(Optional.of(ctx), credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey());
		}

		public Builder(String accessKey, String secretKey) {
			this(BuilderContext.ABSENT, accessKey, secretKey);
		}

		public Builder(BuilderContext ctx, String accessKey, String secretKey) {
			this(Optional.of(ctx), accessKey, secretKey);
		}

		public Builder(BuilderContext ctx) {
			this(Optional.of(ctx), NullUtils.NONE, NullUtils.NONE);
		}

		private Builder(Optional<BuilderContext> ctx, String accessKey, String secretKey) {
			if (ctx.isPresent()) {
				this.accessKey = BuilderUtils.getValue(ctx.get(), ACCESS_KEY, ACCESS_ENV_KEY, accessKey);
				this.secretKey = BuilderUtils.getValue(ctx.get(), SECRET_KEY, SECRET_ENV_KEY, secretKey);
			} else {
				this.accessKey = accessKey;
				this.secretKey = secretKey;
			}
		}

		public ImmutableAwsCredentials build() {
			Assert.noBlanks(accessKey, secretKey);
			Assert.decrypted(secretKey);
			return new ImmutableAwsCredentials(this);
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
