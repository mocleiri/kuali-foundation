package org.kuali.common.aws;

import java.util.List;

import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.Permission;
import org.kuali.common.aws.ec2.model.security.Permissions;
import org.kuali.common.util.Assert;

public enum SecurityGroups {

	CI("ci", "Jenkins CI Server", Permissions.WEB_SERVER);

	private final KualiSecurityGroup group;

	private SecurityGroups(String name, String description, List<Permission> perms) {
		Assert.noBlanks(name, description);
		this.group = new KualiSecurityGroup.Builder(name).description(description).permissions(perms).build();
	}

	public KualiSecurityGroup getGroup() {
		return group;
	}

}
