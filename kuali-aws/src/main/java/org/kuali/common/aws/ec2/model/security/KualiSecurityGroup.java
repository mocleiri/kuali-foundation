package org.kuali.common.aws.ec2.model.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class KualiSecurityGroup {

	private final String name;
	private final Optional<String> description;
	private final List<Permission> permissions;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Optional<String> description = Optional.absent();
		private List<Permission> permissions = ImmutableList.of();

		public Builder(String name) {
			this.name = name;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(NullUtils.trimToNull(description));
			return this;
		}

		public Builder permissions(List<Permission> permissions) {
			this.permissions = permissions;
			return this;
		}

		public KualiSecurityGroup build() {
			Assert.noBlanks(name);
			Assert.noNulls(description, permissions);
			this.permissions = new ArrayList<Permission>(permissions);
			Collections.sort(permissions);
			this.permissions = ImmutableList.copyOf(permissions);
			return new KualiSecurityGroup(this);
		}
	}

	private KualiSecurityGroup(Builder builder) {
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

	public List<Permission> getPermissions() {
		return permissions;
	}

}
