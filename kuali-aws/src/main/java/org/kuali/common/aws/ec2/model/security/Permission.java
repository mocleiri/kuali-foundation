package org.kuali.common.aws.ec2.model.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

import com.google.common.collect.ImmutableList;

public final class Permission implements Comparable<Permission> {

	private final int port;
	private final Protocol protocol;
	private final List<String> cidrNotations;

	public static class Builder {

		// Required
		private final int port;

		// Optional
		private List<String> cidrNotations = ImmutableList.of(CIDR.ANY.getNotation());
		private Protocol protocol = Protocol.TCP;

		public Builder(int port) {
			this.port = port;
		}

		public Builder cidrNotations(List<String> cidrNotations) {
			this.cidrNotations = cidrNotations;
			return this;
		}

		public Builder protocol(Protocol protocol) {
			this.protocol = protocol;
			return this;
		}

		public Permission build() {
			Assert.noNulls(cidrNotations, protocol);
			Assert.noBlanks(cidrNotations);
			Assert.isTrue(cidrNotations.size() >= 1, "Must supply at least one CIDR notation");
			Assert.isPort(port);

			// Changing this has implications for equals(), be careful
			this.cidrNotations = new ArrayList<String>(cidrNotations);
			Collections.sort(cidrNotations);
			this.cidrNotations = ImmutableList.copyOf(cidrNotations);
			return new Permission(this);
		}
	}

	private Permission(Builder builder) {
		this.protocol = builder.protocol;
		this.port = builder.port;
		this.cidrNotations = builder.cidrNotations;
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
	public int compareTo(Permission other) {
		if (port == other.getPort()) {
			return protocol.name().compareTo(other.getProtocol().name());
		} else {
			return Double.compare(port, other.getPort());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + port;
		result = prime * result + protocol.hashCode();
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

		// A non-null object reference is by definition not equal to null
		if (object == null) {
			return false;
		}

		// They are different runtime types and therefore cannot be considered equal
		if (this.getClass() != object.getClass()) {
			return false;
		}

		// Cast to a Permission object
		Permission other = (Permission) object;

		// If the ports are different they are not equal
		if (port != other.getPort()) {
			return false;
		}

		// If the protocols are different they are not equal
		if (protocol != other.getProtocol()) {
			return false;
		}

		// If they both have the exact same list of CIDR notations, they are equal
		// This only works because Builder.build() sorts the CIDR notation list
		return ListUtils.equals(cidrNotations, other.getCidrNotations());
	}

}
