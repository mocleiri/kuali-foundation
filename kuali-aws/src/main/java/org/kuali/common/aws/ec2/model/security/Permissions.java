package org.kuali.common.aws.ec2.model.security;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Permissions {

	SSH(Ports.SSH.getValue(), Protocol.TCP, CIDR.ANY.getNotation()), // Allow SSH (port 22) from anywhere
	HTTP(Ports.HTTP.getValue(), Protocol.TCP, CIDR.ANY.getNotation()), // Allow HTTP (port 80) from anywhere
	HTTPS(Ports.HTTPS.getValue(), Protocol.TCP, CIDR.ANY.getNotation()); // Allow HTTPS (port 443) from anywhere

	private final Permission permission;

	private Permissions(int port, Protocol protocol, String cidrNotation) {
		this(port, protocol, ImmutableList.of(cidrNotation));
	}

	private Permissions(int port, Protocol protocol, List<String> cidrNotations) {
		this.permission = new Permission.Builder(port).protocol(protocol).cidrNotations(cidrNotations).build();
	}

	public Permission getPermission() {
		return permission;
	}

	public static final List<Permission> WEB_SERVER = ImmutableList.of(SSH.getPermission(), HTTP.getPermission(), HTTPS.getPermission());

}
