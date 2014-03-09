package org.kuali.common.aws;

import java.util.List;

import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.Permission;
import org.kuali.common.aws.ec2.model.security.NamedPermission;
import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public enum SecurityGroups {

	CI("ci", "Continuous Integration", NamedPermission.APPLICATION_SERVER_PERMISSIONS), //
	CI_MASTER("ci.master", "Jenkins CI Server - Master", NamedPermission.APPLICATION_SERVER_PERMISSIONS), //
	CI_BUILD_SLAVE("ci.slave", "Jenkins CI Server - Slave", NamedPermission.SSH_PERMISSION.getPermission());

	private final KualiSecurityGroup group;

	private SecurityGroups(String name, String description) {
		this(name, description, ImmutableList.<Permission> of());
	}

	private SecurityGroups(String name, String description, Permission permission) {
		this(name, description, ImmutableList.of(permission));
	}

	private SecurityGroups(String name, String description, List<Permission> perms) {
		Assert.noBlanks(name, description);
		Assert.noNulls(perms);
		this.group = KualiSecurityGroup.builder(name).withDescription(description).withPermissions(perms).build();
	}

	public KualiSecurityGroup getGroup() {
		return group;
	}

}
