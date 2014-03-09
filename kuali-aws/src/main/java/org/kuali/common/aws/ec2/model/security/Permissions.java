package org.kuali.common.aws.ec2.model.security;

import org.kuali.common.util.Ports;

import com.google.common.collect.ImmutableList;

public enum Permissions {

	SSH(Ports.SSH), // Allow SSH (port 22) from anywhere
	HTTP(Ports.HTTP), // Allow HTTP (port 80) from anywhere
	HTTPS(Ports.HTTPS); // Allow HTTPS (port 443) from anywhere

	private final Permission permission;

	private Permissions(int port) {
		this.permission = Permission.create(port);
	}

	public Permission getPermission() {
		return permission;
	}

	public static final ImmutableList<Permission> APPLICATION_SERVER_PERMISSIONS = ImmutableList.of(SSH.getPermission(), HTTP.getPermission(), HTTPS.getPermission());

}
