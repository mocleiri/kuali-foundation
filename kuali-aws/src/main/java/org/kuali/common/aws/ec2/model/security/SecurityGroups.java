package org.kuali.common.aws.ec2.model.security;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum SecurityGroups {

	SSH("ssh", "Allow ssh (port 22) from anywhere", Permissions.SSH.getPermission()), //
	HTTP("http", "Allow http (port 80) from anywhere", Permissions.HTTP.getPermission()), //
	HTTPS("https", "Allow https (port 443) from anywhere", Permissions.HTTPS.getPermission());

	private final ImmutableSecurityGroup securityGroup;

	private SecurityGroups(String name, String description, ImmutableIpPermission permission) {
		this(name, description, ImmutableList.of(permission));
	}

	private SecurityGroups(String name, String description, List<ImmutableIpPermission> permissions) {
		this.securityGroup = new ImmutableSecurityGroup.Builder(name).description(description).permissions(permissions).build();
	}

	public ImmutableSecurityGroup getSecurityGroup() {
		return securityGroup;
	}

}
