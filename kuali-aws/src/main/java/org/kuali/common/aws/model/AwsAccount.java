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

		// This provides a simple way for system properties / environment variables to override configuration
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
			this.env = env;
			if (env.isPresent()) {
				EnvironmentService service = env.get();
				this.name = service.getString(NAME_KEY, name);
				this.accountNumber = service.getString(ACCOUNT_NUMBER_KEY, accountNumber);
			} else {
				this.name = name;
				this.accountNumber = accountNumber;
			}
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(NullUtils.trimToNull(description));
			return this;
		}

		public AwsAccount build() {
			Assert.noBlanks(name, accountNumber);
			if (env.isPresent()) {
				this.description = SpringUtils.getString(env.get(), ACCOUNT_DESCRIPTION_KEY, description);
			}
			Assert.noNulls(description);
			return new AwsAccount(this);
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
