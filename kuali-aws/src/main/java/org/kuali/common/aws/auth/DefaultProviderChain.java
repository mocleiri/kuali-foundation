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
	private final boolean instanceCredentialsOverride;
	private final List<AWSCredentialsProvider> providers;

	public static class Builder {

		private Optional<EncryptionService> enc = Optional.absent();
		private Optional<AWSCredentials> optionalCredentials = Optional.absent();
		private boolean instanceCredentialsOverride = false;

		private List<AWSCredentialsProvider> providers;

		public Builder enc(EncryptionService enc) {
			this.enc = Optional.of(enc);
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

		private void validate(DefaultProviderChain provider) {
			Assert.noNulls(provider.getCredentials(), provider.getEnc(), provider.getProviders());
			Assert.isTrue(provider.getProviders().size() > 0, "Must supply at least one provider");
		}

		public AWSCredentialsProvider build() {
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

			// Then fall through to the provided credentials if they are present
			if (optionalCredentials.isPresent()) {
				providers.add(new SimpleAWSCredentialsProvider(optionalCredentials.get()));
			}

			// Amazon's EC2 Instance Metadata Service
			// http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/java-dg-roles.html
			// This allows you to setup an IAM role, attach that role to an EC2 Instance at launch time,
			// and thus automatically authorize java code running on an EC2 instance
			if (instanceCredentialsOverride) {
				// Add them as the first element in the list if the override flag is set
				providers.add(0, new InstanceProfileCredentialsProvider());
			} else {
				// Otherwise add them at the end
				providers.add(new InstanceProfileCredentialsProvider());
			}

			// Convert the list into an array
			return ImmutableList.copyOf(providers);
		}
	}

	public DefaultProviderChain(Builder builder) {
		this.optionalCredentials = builder.optionalCredentials;
		this.enc = builder.enc;
		this.instanceCredentialsOverride = builder.instanceCredentialsOverride;
		this.providers = builder.providers;
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
		if (!enc.isPresent()) {
			return creds;
		}
		String accessKey = creds.getAWSAccessKeyId();
		String secretKey = EncUtils.decrypt(enc.get(), creds.getAWSSecretKey());
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

}
