/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.dns.dnsme.model;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

public final class DNSMadeEasyCredentials {

	private final String apiKey;
	private final String secretKey;

	public String getApiKey() {
		return apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	private DNSMadeEasyCredentials(Builder builder) {
		this.apiKey = builder.apiKey;
		this.secretKey = builder.secretKey;
	}

	public static class Builder {

		private String apiKey;
		private String secretKey;

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public Builder secretKey(String secretKey) {
			this.secretKey = secretKey;
			return this;
		}

		public DNSMadeEasyCredentials build() {
			DNSMadeEasyCredentials instance = new DNSMadeEasyCredentials(this);
			validate(instance);
			return instance;
		}

		private static void validate(DNSMadeEasyCredentials instance) {
			checkArgument(!StringUtils.isBlank(instance.apiKey), "'apiKey' cannot be blank");
			checkArgument(!StringUtils.isBlank(instance.secretKey), "'secretKey' cannot be blank");
		}

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}
	}

}
