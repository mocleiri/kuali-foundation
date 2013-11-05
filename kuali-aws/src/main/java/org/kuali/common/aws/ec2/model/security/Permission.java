package org.kuali.common.aws.ec2.model.security;

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
			Assert.isTrue(port >= 0 && port <= 65535, "Port must be a number between 0 and 65535");
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
			return protocol.compareTo(other.getProtocol());
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
		if (object == null) {
			return false;
		}

		if (this.getClass() != object.getClass()) {
			return false;
		}

		Permission other = (Permission) object;

		if (port != other.getPort()) {
			return false;
		}

		if (protocol != other.getProtocol()) {
			return false;
		}

		return ListUtils.equals(cidrNotations, other.getCidrNotations());
	}

}
