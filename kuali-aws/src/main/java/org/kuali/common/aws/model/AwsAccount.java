package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

public final class AwsAccount {

	private final String accountNumber;
	private final String accessKeyId;
	private final String keyName;

	public static class Builder {

		// Required
		private final String accountNumber;
		private String accessKeyId;
		private String keyName;

		public Builder(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Builder accessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
			return this;
		}

		public Builder keyName(String keyName) {
			this.keyName = keyName;
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(accountNumber, accessKeyId, keyName);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.accountNumber = builder.accessKeyId;
		this.accessKeyId = builder.accessKeyId;
		this.keyName = builder.keyName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public String getKeyName() {
		return keyName;
	}

}
