package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public final class AwsAccount {

	private final String name;
	private final Optional<String> accountNumber;
	private final Optional<AWSCredentials> credentials;
	private final Optional<KeyPair> key;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Optional<String> accountNumber = Optional.absent();
		private Optional<AWSCredentials> credentials = Optional.absent();
		private Optional<KeyPair> key = Optional.absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder accountNumber(String accountNumber) {
			this.accountNumber = Optional.fromNullable(NullUtils.trimToNull(accountNumber));
			return this;
		}

		public Builder credentials(AWSCredentials credentials) {
			this.credentials = Optional.fromNullable(credentials);
			return this;
		}

		public Builder key(KeyPair key) {
			this.key = Optional.fromNullable(key);
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(name);
			Assert.noNulls(credentials, key, accountNumber);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.name = builder.name;
		this.accountNumber = builder.accountNumber;
		this.credentials = builder.credentials;
		this.key = builder.key;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getAccountNumber() {
		return accountNumber;
	}

	public Optional<AWSCredentials> getCredentials() {
		return credentials;
	}

	public Optional<KeyPair> getKey() {
		return key;
	}

}
