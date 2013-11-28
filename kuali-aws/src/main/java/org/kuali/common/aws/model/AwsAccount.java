package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;

public final class AwsAccount {

	private final String name;
	private final String accountNumber;
	private final Optional<String> description;

	public static class Builder {

		// Required
		private final String name;
		private final String accountNumber;

		// Optional
		private Optional<String> description = Optional.absent();

		// Allow system properties / environment variables to override explicitly supplied values
		private Optional<EnvironmentService> env = Optional.absent();
		private static final String NAME_KEY = "aws.account";
		private static final String ACCOUNT_NUMBER_KEY = "aws.accountNumber";
		private static final String ACCOUNT_DESCRIPTION_KEY = "aws.accountDescription";

		public Builder(String name, String accountNumber) {
			this(Optional.<EnvironmentService> absent(), name, accountNumber);
		}

		public Builder(EnvironmentService env, String name, String accountNumber) {
			this(Optional.of(env), name, accountNumber);
		}

		private Builder(Optional<EnvironmentService> env, String name, String accountNumber) {
			if (env.isPresent()) {
				this.name = env.get().getString(NAME_KEY, name);
				this.accountNumber = env.get().getString(ACCOUNT_NUMBER_KEY, accountNumber);
			} else {
				this.name = name;
				this.accountNumber = accountNumber;
			}
			this.env = env;
		}

		public Builder description(String description) {
			this.description = NullUtils.toAbsent(description);
			return this;
		}

		private void override() {
			if (env.isPresent()) {
				description(SpringUtils.getString(env.get(), ACCOUNT_DESCRIPTION_KEY, description).orNull());
			}
		}

		private void finish() {
			override();
		}

		private void validate(AwsAccount account) {
			Assert.noBlanks(account.getName(), account.getAccountNumber());
			Assert.noNulls(account.getDescription());
		}

		public AwsAccount build() {
			finish();
			AwsAccount account = new AwsAccount(this);
			validate(account);
			return account;
		}

	}

	private AwsAccount(Builder builder) {
		this.name = builder.name;
		this.accountNumber = builder.accountNumber;
		this.description = builder.description;
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
