package org.kuali.common.aws.ec2.model.security;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class ImmutableSecurityGroup {

	private final String name;
	private final Optional<String> description;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Optional<String> description = Optional.absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(NullUtils.trimToNull(description));
			return this;
		}

		public ImmutableSecurityGroup build() {
			Assert.noBlanks(name);
			Assert.noNulls(description);
			return new ImmutableSecurityGroup(this);
		}
	}

	private ImmutableSecurityGroup(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getDescription() {
		return description;
	}

}
