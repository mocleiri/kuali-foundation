package org.kuali.common.aws.ec2.model.security;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class ImmutableIpPermission {

	private final Protocol protocol;
	private final int port;
	private final List<String> cidrNotations;

	public static class Builder {

		// Required
		private final int port;

		// Optional
		private List<String> cidrNotations = ImmutableList.of(CIDR.ANY.getValue());
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

		public ImmutableIpPermission build() {
			Assert.noNulls(cidrNotations, protocol);
			Assert.noBlanks(cidrNotations);
			Assert.isTrue(cidrNotations.size() >= 1, "Must supply at least one CIDR notation");
			Assert.isTrue(port >= 0 && port <= 65535, "Port must be a number between 0 and 65535");
			this.cidrNotations = ImmutableList.copyOf(cidrNotations);
			return new ImmutableIpPermission(this);
		}
	}

	private ImmutableIpPermission(Builder builder) {
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

}
