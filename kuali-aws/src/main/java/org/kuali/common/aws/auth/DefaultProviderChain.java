/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.auth;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.model.ImmutableCredentials;
import org.kuali.common.aws.model.ImmutableSessionCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * This chain searches for AWS credentials in system properties -> environment variables -> Amazon's EC2 Instance Metadata Service
 */
public final class DefaultProviderChain extends AWSCredentialsProviderChain {

	private final Optional<AWSCredentials> optionalCredentials;
	private final Optional<EncryptionService> enc;
	private final Optional<EnvironmentService> env;
	private final boolean instanceCredentialsOverride;
	private final List<AWSCredentialsProvider> providers;

	public static class Builder {

		// Optional
		private Optional<EncryptionService> enc = Optional.absent();
		private Optional<EnvironmentService> env = Optional.absent();
		private Optional<AWSCredentials> optionalCredentials = Optional.absent();
		private boolean instanceCredentialsOverride = false; // If true, EC2 instance credentials take precedence over optionalCredentials

		// Filled in by the build() method
		private List<AWSCredentialsProvider> providers;

		private static final String INSTANCE_CREDENTIALS_OVERRIDE_KEY = "aws.instanceCredentialsOverride";

		public Builder enc(EncryptionService enc) {
			this.enc = Optional.of(enc);
			return this;
		}

		public Builder env(EnvironmentService env) {
			this.env = Optional.of(env);
			return this;
		}

		public Builder credentials(AWSCredentials credentials) {
			this.optionalCredentials = Optional.of(credentials);
			return this;
		}

		public Builder instanceCredentialsOverride(boolean instanceCredentialsOverride) {
			this.instanceCredentialsOverride = instanceCredentialsOverride;
			return this;
		}

		private void override() {
			if (env.isPresent()) {
				instanceCredentialsOverride(env.get().getBoolean(INSTANCE_CREDENTIALS_OVERRIDE_KEY, instanceCredentialsOverride));
			}
		}

		private void validate(DefaultProviderChain provider) {
			Assert.noNulls(provider.getCredentials(), provider.getEnc(), provider.getEnv(), provider.getProviders());
			Assert.isTrue(provider.getProviders().size() > 0, "Must supply at least one provider");
		}

		public AWSCredentialsProvider build() {
			override();
			this.providers = getProviders();
			DefaultProviderChain provider = new DefaultProviderChain(this);
			validate(provider);
			return provider;
		}

		private List<AWSCredentialsProvider> getProviders() {

			// Null not allowed
			Assert.noNulls(optionalCredentials);

			// Set up some storage
			List<AWSCredentialsProvider> providers = new ArrayList<AWSCredentialsProvider>();

			// System properties always win
			providers.add(new SystemPropertiesCredentialsProvider());

			// Then fall through to environment variables
			providers.add(new EnvironmentVariableCredentialsProvider());

			// Then fall through to "other" providers
			// Amazon's EC2 Instance Metadata Service
			// http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/java-dg-roles.html
			// AWS allows you to setup an IAM role and attach that role to an EC2 Instance at launch time
			// This allows you to automatically authorize java code running on an EC2 instance
			if (optionalCredentials.isPresent()) {
				if (instanceCredentialsOverride) {
					// The EC2 instance credentials (if present) take precedence over the provided credentials
					providers.add(new InstanceProfileCredentialsProvider());
				}
				// Fall through to the credentials they provided
				providers.add(new SimpleAWSCredentialsProvider(optionalCredentials.get()));
			} else {
				// Use EC2 instance credentials as a last resort if no credentials were provided
				providers.add(new InstanceProfileCredentialsProvider());
			}

			// Make the list immutable
			return ImmutableList.copyOf(providers);
		}
	}

	public DefaultProviderChain(Builder builder) {
		super(toArray(builder.providers));
		this.optionalCredentials = builder.optionalCredentials;
		this.enc = builder.enc;
		this.env = builder.env;
		this.instanceCredentialsOverride = builder.instanceCredentialsOverride;
		this.providers = builder.providers;
	}

	private static AWSCredentialsProvider[] toArray(List<AWSCredentialsProvider> providers) {
		return providers.toArray(new AWSCredentialsProvider[providers.size()]);
	}

	public Optional<AWSCredentials> getOptionalCredentials() {
		return optionalCredentials;
	}

	public boolean isInstanceCredentialsOverride() {
		return instanceCredentialsOverride;
	}

	@Override
	public AWSCredentials getCredentials() {
		AWSCredentials creds = super.getCredentials();
		String accessKey = creds.getAWSAccessKeyId();
		String secretKey = EncUtils.decrypt(enc, creds.getAWSSecretKey());
		if (creds instanceof AWSSessionCredentials) {
			AWSSessionCredentials sessionCreds = (AWSSessionCredentials) creds;
			return new ImmutableSessionCredentials(accessKey, secretKey, sessionCreds.getSessionToken());
		} else {
			return new ImmutableCredentials.Builder(accessKey, secretKey).build();
		}
	}

	public List<AWSCredentialsProvider> getProviders() {
		return providers;
	}

	public Optional<EncryptionService> getEnc() {
		return enc;
	}

	public Optional<EnvironmentService> getEnv() {
		return env;
	}

}
