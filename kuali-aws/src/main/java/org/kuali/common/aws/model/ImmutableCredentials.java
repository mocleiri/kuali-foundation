/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSSessionCredentials;
import com.google.common.base.Optional;

public class ImmutableCredentials implements AWSCredentials {

	private final String accessKey;
	private final String secretKey;

	public static class Builder {

		// Required
		private final String accessKey;
		private final String secretKey;

		// Optional
		private boolean assertNotEncrypted = true;
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

		public Builder assertNotEncrypted(boolean assertNotEncrypted) {
			this.assertNotEncrypted = assertNotEncrypted;
			return this;
		}

		private void validate(AWSCredentials creds, boolean assertNotEncrypted) {
			Assert.noBlanks(creds.getAWSAccessKeyId(), creds.getAWSSecretKey());
			if (assertNotEncrypted) {
				Assert.notEncrypted(creds.getAWSSecretKey());
			}
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
				return new ImmutableSessionCredentials(accessKey, secretKey, sessionToken);
			} else {
				return new ImmutableCredentials(builder);
			}
		}

		public AWSCredentials build() {
			boolean assertNotEncrypted = this.assertNotEncrypted; // Do this for thread safety
			AWSCredentials creds = getCredentials(this);
			validate(creds, assertNotEncrypted);
			return creds;
		}

	}

	private ImmutableCredentials(Builder builder) {
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
