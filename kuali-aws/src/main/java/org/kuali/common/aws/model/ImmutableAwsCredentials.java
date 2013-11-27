package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.builder.BuilderContext;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public class ImmutableAwsCredentials implements AWSCredentials {

	private final String accessKey;
	private final String secretKey;

	public static class Builder {

		// Required
		private final String accessKey;
		private final String secretKey;

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

		private Builder(Optional<BuilderContext> ctx, String accessKey, String secretKey) {
			if (ctx.isPresent()) {
				EnvironmentService env = ctx.get().getEnv();
				EncryptionService enc = ctx.get().getEnc();
				this.accessKey = env.getString("aws.accessKey", accessKey);
				this.secretKey = EncUtils.decrypt(enc, env.getString("aws.secretKey", secretKey));
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
