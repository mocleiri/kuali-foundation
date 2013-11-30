package org.kuali.common.aws.auth;

import java.util.List;

import org.kuali.common.aws.model.ImmutableCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.google.common.collect.ImmutableList;

public final class EnvCredentialsProvider implements AWSCredentialsProvider {

	private final EnvironmentService env;
	private final List<String> accessKeyProperties;
	private final List<String> secretKeyProperties;

	public static class Builder {

		// Required
		private final EnvironmentService env;

		// Optional (default values are usually good enough)
		private List<String> accessKeyProperties = ImmutableList.of("aws.accessKey", "aws.accessKeyId");
		private List<String> secretKeyProperties = ImmutableList.of("aws.secretKey", "aws.secretAccessKey");

		// Allow them to override which system properties / environment variables get examined
		private static final String ACCESS_KEY_PROPERTIES = "aws.accessKeyProperties";
		private static final String SECRET_KEY_PROPERTIES = "aws.secretKeyProperties";

		public Builder(EnvironmentService env) {
			this.env = env;
		}

		public Builder accessKeyProperties(List<String> accessKeyProperties) {
			this.accessKeyProperties = accessKeyProperties;
			return this;
		}

		public Builder secretKeyProperties(List<String> secretKeyProperties) {
			this.secretKeyProperties = secretKeyProperties;
			return this;
		}

		private void validate(EnvCredentialsProvider provider) {
			Assert.noNulls(provider.getEnv(), provider.getAccessKeyProperties(), provider.getSecretKeyProperties());
			Assert.isTrue(provider.getAccessKeyProperties().size() > 0, "Must provide at least one property to examine for AWS Access Key ID");
			Assert.isTrue(provider.getSecretKeyProperties().size() > 0, "Must provide at least one property to examine for AWS Secret Key");
		}

		private void override() {
			accessKeyProperties(SpringUtils.getStrings(env, ACCESS_KEY_PROPERTIES, accessKeyProperties));
			secretKeyProperties(SpringUtils.getStrings(env, SECRET_KEY_PROPERTIES, secretKeyProperties));
		}

		private void finish() {
			override();
			this.accessKeyProperties = ImmutableList.copyOf(accessKeyProperties);
			this.secretKeyProperties = ImmutableList.copyOf(secretKeyProperties);
		}

		public EnvCredentialsProvider build() {
			finish();
			EnvCredentialsProvider provider = new EnvCredentialsProvider(this);
			validate(provider);
			return provider;
		}

	}

	private EnvCredentialsProvider(Builder builder) {
		this.env = builder.env;
		this.accessKeyProperties = builder.accessKeyProperties;
		this.secretKeyProperties = builder.secretKeyProperties;
	}

	@Override
	public AWSCredentials getCredentials() {
		String accessKey = SpringUtils.getString(env, accessKeyProperties);
		String secretKey = SpringUtils.getString(env, accessKeyProperties);
		return new ImmutableCredentials.Builder(accessKey, secretKey).assertNotEncrypted(false).build();
	}

	@Override
	public void refresh() {
		// noop
	}

	public EnvironmentService getEnv() {
		return env;
	}

	public List<String> getAccessKeyProperties() {
		return accessKeyProperties;
	}

	public List<String> getSecretKeyProperties() {
		return secretKeyProperties;
	}

}
