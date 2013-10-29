package org.kuali.common.aws.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class AwsAccount {

	private final String accountNumber;
	private final String accessKeyId;
	private final String username;
	private final List<String> securityGroups;

	public static class Builder {

		// Required
		private final String accountNumber;
		private final String accessKeyId;
		private final String username;

		// Optional
		private List<String> securityGroups = ImmutableList.of();

		public Builder(String accountNumber, String accessKeyId, String username) {
			this.accountNumber = accountNumber;
			this.accessKeyId = accessKeyId;
			this.username = username;
		}

		public AwsAccount build() {
			Assert.noBlanks(accountNumber, accessKeyId, username);
			Assert.noNulls(securityGroups);
			this.securityGroups = ImmutableList.copyOf(securityGroups);
			return new AwsAccount(this);
		}

	}

	private AwsAccount(Builder builder) {
		this.accountNumber = builder.accessKeyId;
		this.accessKeyId = builder.accessKeyId;
		this.username = builder.username;
		this.securityGroups = builder.securityGroups;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getSecurityGroups() {
		return securityGroups;
	}

}
