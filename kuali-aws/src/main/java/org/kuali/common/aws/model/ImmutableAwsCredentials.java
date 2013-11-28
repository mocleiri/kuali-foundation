package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSSessionCredentials;
import com.google.common.base.Optional;

public class ImmutableAwsCredentials implements AWSCredentials {

	private final String accessKey;
	private final String secretKey;

	public static class Builder {

		// Required
		private final String accessKey;
		private final String secretKey;

		// Optional
		private Optional<String> sessionToken = Optional.absent();

		/**
		 * Get a set of AWS credentials from the provider
		 */
		public Builder(AWSCredentialsProvider provider) {
			this(Optional.of(provider), Optional.<String> absent(), Optional.<String> absent());
		}

		/**
		 * Create a set of AWS credentials from the string values.
		 */
		public Builder(String accessKey, String secretKey) {
			this(Optional.<AWSCredentialsProvider> absent(), Optional.of(accessKey), Optional.of(secretKey));
		}

		public Builder sessionToken(String sessionToken) {
			this.sessionToken = NullUtils.toAbsent(sessionToken);
			return this;
		}

		private Builder(Optional<AWSCredentialsProvider> provider, Optional<String> accessKey, Optional<String> secretKey) {
			if (provider.isPresent()) {
				AWSCredentials provided = provider.get().getCredentials();
				this.accessKey = provided.getAWSAccessKeyId();
				this.secretKey = provided.getAWSSecretKey();
				if (provided instanceof AWSSessionCredentials) {
					AWSSessionCredentials sessionCreds = (AWSSessionCredentials) provided;
					sessionToken(sessionCreds.getSessionToken());
				}
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

		private AWSCredentials getCredentials(Builder builder) {
			if (builder.sessionToken.isPresent()) {
				String accessKey = builder.accessKey;
				String secretKey = builder.secretKey;
				String sessionToken = builder.sessionToken.get();
				return new ImmutableAwsSessionCredentials(accessKey, secretKey, sessionToken);
			} else {
				return new ImmutableAwsCredentials(builder);
			}
		}

		public AWSCredentials build() {
			AWSCredentials creds = getCredentials(this);
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
