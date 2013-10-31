package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public final class AwsAccount {

	private final String name;
	private final String accountNumber;
	private final Optional<AWSCredentials> credentials;
	private final Optional<AwsKey> key;

	public static class Builder {

		// Required
		private final String name;
		private final String accountNumber;

		// Optional
		private Optional<AWSCredentials> credentials = Optional.absent();
		private Optional<AwsKey> key = Optional.absent();

		public Builder(String name, String accountNumber) {
			this.name = name;
			this.accountNumber = accountNumber;
		}

		public Builder credentials(AWSCredentials credentials) {
			this.credentials = Optional.fromNullable(credentials);
			return this;
		}

		public Builder AwsKey(AwsKey key) {
			this.key = Optional.fromNullable(key);
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(name, accountNumber);
			Assert.noNulls(credentials, key);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.name = builder.name;
		this.accountNumber = builder.accountNumber;
		this.credentials = builder.credentials;
		this.key = builder.key;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Optional<AWSCredentials> getCredentials() {
		return credentials;
	}

	public Optional<AwsKey> getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

}
