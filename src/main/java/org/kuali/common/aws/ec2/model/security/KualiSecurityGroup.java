/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.ec2.model.security;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;

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
			this.permissions = newArrayList(permissions);
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
