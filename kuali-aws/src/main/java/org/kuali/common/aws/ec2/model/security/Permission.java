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

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.ListUtils.equalElements;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.core.validate.annotation.ValidPort;
import org.kuali.common.util.ObjectUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;

/**
 * This is an immutable, slimmed down, version of the comparable AWS permissions object. Only supports a single port (no port ranges) and CIDR notations.
 */
@IdiotProofImmutable
@JsonDeserialize(builder = Permission.Builder.class)
public final class Permission {

	@ValidPort
	private final int port;

	private final Protocol protocol;

	@NotEmpty
	private final ImmutableList<String> cidrNotations;

	public static Permission create(int port) {
		return builder(port).build();
	}

	public static Builder builder(int port) {
		return new Builder(port);
	}

	public static class Builder extends ValidatingBuilder<Permission> {

		// Required
		private final int port;

		// Optional
		private List<String> cidrNotations = newArrayList(CIDR.ANY.getNotation());
		private Protocol protocol = Protocol.TCP;

		public Builder(int port) {
			this.port = port;
		}

		public Builder withCidrNotations(List<String> cidrNotations) {
			this.cidrNotations = cidrNotations;
			return this;
		}

		public Builder withProtocol(Protocol protocol) {
			this.protocol = protocol;
			return this;
		}

		@Override
		public Permission build() {
			// Changing this has implications for equals(), be careful
			this.cidrNotations = newArrayList(cidrNotations);
			Collections.sort(cidrNotations);
			return validate(new Permission(this));
		}
	}

	private Permission(Builder builder) {
		this.protocol = builder.protocol;
		this.port = builder.port;
		this.cidrNotations = ImmutableList.copyOf(builder.cidrNotations);
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public int getPort() {
		return port;
	}

	public List<String> getCidrNotations() {
		return cidrNotations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = Objects.hashCode(port, protocol);
		for (String notation : cidrNotations) {
			result = result * prime + notation.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object object) {

		// They are the same object
		if (this == object) {
			return true;
		}

		// Make sure object isn't null and is a Permission object
		if (ObjectUtils.notEqual(this, object)) {
			return false;
		}

		// Cast to a Permission object
		Permission a = this;
		Permission b = (Permission) object;

		// If they both have the exact same list of CIDR notations, they are equal
		// This only works because Builder.build() sorts the CIDR notation list
		return Objects.equal(a.port, b.port) && Objects.equal(a.protocol, b.protocol) && equalElements(a.cidrNotations, b.cidrNotations);

	}

	// Singleton enum pattern
	public enum DefaultComparator implements Comparator<Permission> {
		INSTANCE;

		@Override
		public int compare(Permission one, Permission two) {
			return ComparisonChain.start().compare(one.getPort(), two.getPort()).compare(one.getProtocol(), two.getProtocol()).result();
		}
	}

}
