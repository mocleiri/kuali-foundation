package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public final class AwsAccount {

	private final String name;
	private final Optional<String> accountNumber;
	private final AWSCredentials credentials;
	private final KeyPair keyPair;

	public static class Builder {

		// Required
		private final String name;
		private final AWSCredentials credentials;
		private final KeyPair keyPair;

		// Optional
		private Optional<String> accountNumber = Optional.absent();

		public Builder(String name, AWSCredentials credentials, KeyPair keyPair) {
			this.name = name;
			this.credentials = credentials;
			this.keyPair = keyPair;
		}

		public Builder accountNumber(String accountNumber) {
			this.accountNumber = Optional.fromNullable(NullUtils.trimToNull(accountNumber));
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(name);
			Assert.noNulls(credentials, keyPair, accountNumber);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.name = builder.name;
		this.accountNumber = builder.accountNumber;
		this.credentials = builder.credentials;
		this.keyPair = builder.keyPair;
	}

	public String getName() {
		return name;
	}

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public Optional<String> getAccountNumber() {
		return accountNumber;
	}

}
