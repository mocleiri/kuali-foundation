/**
 * Copyright 2014-2014 The Kuali Foundation
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
package org.kuali.common.core.json;

import static com.google.common.base.Optional.fromNullable;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;

@IdiotProofImmutable
@JsonDeserialize(builder = AwsAccount.Builder.class)
public final class AwsAccount {

	private final String name;
	private final String accountNumber;
	private final Optional<String> description;

	private AwsAccount(Builder builder) {
		this.name = builder.name;
		this.accountNumber = builder.accountNumber;
		this.description = builder.description;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<AwsAccount> {

		private String name;
		private String accountNumber;
		private Optional<String> description;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder withDescription(Optional<String> description) {
			this.description = description;
			return this;
		}

		public Builder withDescription(String description) {
			return withDescription(fromNullable(description));
		}

		@Override
		public AwsAccount build() {
			return validate(new AwsAccount(this));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Optional<String> getDescription() {
			return description;
		}

		public void setDescription(Optional<String> description) {
			this.description = description;
		}
	}

	public String getName() {
		return name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Optional<String> getDescription() {
		return description;
	}
}
