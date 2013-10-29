package org.kuali.common.aws.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class AwsAccount {

	private final String accountNumber;
	private final String accessKeyId;
	private final String keyName;
	private final List<String> securityGroups;

	public static class Builder {

		// Required
		private final String accountNumber;
		private String accessKeyId;
		private List<String> securityGroups = ImmutableList.of();
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

		public Builder securityGroups(List<String> securityGroups) {
			this.securityGroups = securityGroups;
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(accountNumber, accessKeyId, keyName);
			Assert.noNulls(securityGroups);
			this.securityGroups = ImmutableList.copyOf(securityGroups);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.accountNumber = builder.accessKeyId;
		this.accessKeyId = builder.accessKeyId;
		this.keyName = builder.keyName;
		this.securityGroups = builder.securityGroups;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public List<String> getSecurityGroups() {
		return securityGroups;
	}

	public String getKeyName() {
		return keyName;
	}

}
