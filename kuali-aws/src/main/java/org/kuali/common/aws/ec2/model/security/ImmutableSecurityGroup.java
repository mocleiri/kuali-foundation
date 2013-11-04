package org.kuali.common.aws.ec2.model.security;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class ImmutableSecurityGroup {

	private final String name;
	private final Optional<String> description;
	private final List<ImmutableIpPermission> permissions;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Optional<String> description = Optional.absent();
		private List<ImmutableIpPermission> permissions = ImmutableList.of();

		public Builder(String name) {
			this.name = name;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(NullUtils.trimToNull(description));
			return this;
		}

		public Builder permissions(List<ImmutableIpPermission> permissions) {
			this.permissions = permissions;
			return this;
		}

		public ImmutableSecurityGroup build() {
			Assert.noBlanks(name);
			Assert.noNulls(description, permissions);
			this.permissions = ImmutableList.copyOf(permissions);
			return new ImmutableSecurityGroup(this);
		}
	}

	private ImmutableSecurityGroup(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.permissions = builder.permissions;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getDescription() {
		return description;
	}

	public List<ImmutableIpPermission> getPermissions() {
		return permissions;
	}

}
