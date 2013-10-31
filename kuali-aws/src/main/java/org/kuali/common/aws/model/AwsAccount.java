package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;

public final class AwsAccount {

	private final String accountNumber;
	private final AWSCredentials credentials;
	private final AwsKey key;

	public static class Builder {

		// Required
		private final String accountNumber;

		// Optional
		private AWSCredentials credentials;
		private AwsKey key;

		public Builder(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Builder credentials(AWSCredentials credentials) {
			this.credentials = credentials;
			return this;
		}

		public Builder AwsKey(AwsKey key) {
			this.key = key;
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(accountNumber);
			Assert.noNulls(credentials, key);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.accountNumber = builder.accountNumber;
		this.credentials = builder.credentials;
		this.key = builder.key;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public AwsKey getKey() {
		return key;
	}

}
