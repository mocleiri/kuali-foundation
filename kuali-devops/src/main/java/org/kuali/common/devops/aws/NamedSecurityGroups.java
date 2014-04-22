package org.kuali.common.devops.aws;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.aws.ec2.model.security.NamedPermissions.ALLOW_SSH_FROM_ANYWHERE;
import static org.kuali.common.aws.ec2.model.security.NamedPermissions.APPLICATION_SERVER_PERMISSIONS;

import java.util.List;

import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.Permission;

import com.google.common.collect.ImmutableList;

public enum NamedSecurityGroups {

	// That is just a group with no perm's so that the slaves and master belong to the same group
	// Amazon gives servers belonging to a common group unrestricted network access to each other
	CI("ci", "Continuous Integration", ImmutableList.<Permission> of()), //
	CI_MASTER("ci.master", "Jenkins - Master", getMasterPermissions()), //
	CI_BUILD_SLAVE("ci.slave", "Jenkins - Slave", ALLOW_SSH_FROM_ANYWHERE.getPermission());

	private final KualiSecurityGroup group;

	private NamedSecurityGroups(String name, String description, Permission permission) {
		this(name, description, ImmutableList.of(permission));
	}

	private NamedSecurityGroups(String name, String description, Permission... perms) {
		this(name, description, ImmutableList.copyOf(perms));
	}

	private NamedSecurityGroups(String name, String description, List<Permission> perms) {
		this.group = KualiSecurityGroup.builder(name).withDescription(description).withPermissions(perms).build();
	}

	public KualiSecurityGroup getGroup() {
		return group;
	}

	// This is the port Jenkins internal SSH server is listening on
	private static final int JENKINS_MASTER_SSH_PORT = 45358;

	private static List<Permission> getMasterPermissions() {
		List<Permission> perms = newArrayList();
		perms.addAll(APPLICATION_SERVER_PERMISSIONS);
		perms.add(Permission.create(JENKINS_MASTER_SSH_PORT));
		return ImmutableList.copyOf(perms);
	}

}
