package org.kuali.common.devops.aws;

import java.util.List;

import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.Permission;
import org.kuali.common.aws.ec2.model.security.Permissions;

import com.google.common.collect.ImmutableList;

public enum SecurityGroups {

	// TODO Remove the Permissions.WEB_SERVER from the CI security group here
	// That is just a group with no perm's so that the slaves and master belong to the same group and thus
	// have unrestricted network access to each other from Amazon's perspective
	CI("ci", "Continuous Integration", Permissions.APPLICATION_SERVER_PERMISSIONS), //
	CI_MASTER("ci.master", "Jenkins CI Server - Master", Permissions.APPLICATION_SERVER_PERMISSIONS), //
	CI_BUILD_SLAVE("ci.slave", "Jenkins CI Server - Slave", Permissions.SSH.getPermission());

	private final KualiSecurityGroup group;

	private SecurityGroups(String name, String description) {
		this(name, description, ImmutableList.<Permission> of());
	}

	private SecurityGroups(String name, String description, Permission permission) {
		this(name, description, ImmutableList.of(permission));
	}

	private SecurityGroups(String name, String description, List<Permission> perms) {
		this.group = KualiSecurityGroup.builder(name).withDescription(description).withPermissions(perms).build();
	}

	public KualiSecurityGroup getGroup() {
		return group;
	}

}
