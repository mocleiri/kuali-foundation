package org.kuali.common.aws.ec2.model.security;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class KualiSecurityGroup {

	private final String name;
	private final Optional<String> description;
	private final ImmutableList<Permission> permissions;

	public static Builder builder(String name) {
		return new Builder(name);
	}

	public static class Builder extends ValidatingBuilder<KualiSecurityGroup> {

		// Required
		private final String name;

		// Optional
		private Optional<String> description = absent();
		private List<Permission> permissions = newArrayList();

		public Builder(String name) {
			this.name = name;
		}

		public Builder withDescription(Optional<String> description) {
			this.description = description;
			return this;
		}

		public Builder withDescription(String description) {
			return withDescription(Optional.of(description));
		}

		public Builder withPermissions(List<Permission> permissions) {
			this.permissions = permissions;
			return this;
		}

		public Builder addPermission(Permission permission) {
			this.permissions.add(permission);
			return this;
		}

		@Override
		public KualiSecurityGroup build() {
			this.permissions = new ArrayList<Permission>(permissions);
			Collections.sort(permissions, Permission.DefaultComparator.INSTANCE);
			return validate(new KualiSecurityGroup(this));
		}
	}

	private KualiSecurityGroup(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.permissions = ImmutableList.copyOf(builder.permissions);
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
