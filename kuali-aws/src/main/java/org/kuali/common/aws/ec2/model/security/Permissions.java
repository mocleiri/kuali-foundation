package org.kuali.common.aws.ec2.model.security;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Permissions {

	SSH(Port.SSH.getValue(), Protocol.TCP, CIDR.ANY.getNotation()), // Allow SSH (port 22) from anywhere
	HTTP(Port.HTTP.getValue(), Protocol.TCP, CIDR.ANY.getNotation()), // Allow HTTP (port 80) from anywhere
	HTTPS(Port.HTTP.getValue(), Protocol.TCP, CIDR.ANY.getNotation()); // Allow HTTPS (port 443) from anywhere

	private final ImmutableIpPermission permission;

	private Permissions(int port, Protocol protocol, String cidrNotation) {
		this(port, protocol, ImmutableList.of(cidrNotation));
	}

	private Permissions(int port, Protocol protocol, List<String> cidrNotations) {
		this.permission = new ImmutableIpPermission.Builder(port).protocol(protocol).cidrNotations(cidrNotations).build();
	}

	public ImmutableIpPermission getPermission() {
		return permission;
	}

}
