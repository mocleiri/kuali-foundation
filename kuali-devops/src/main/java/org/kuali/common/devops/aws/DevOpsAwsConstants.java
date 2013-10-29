package org.kuali.common.devops.aws;

import java.util.List;

import org.kuali.common.aws.model.AwsAccount;

public abstract class DevOpsAwsConstants {

	private static final List<String> DEFAULT_GROUPS = SecurityGroupName.getSecurityGroups(SecurityGroupName.SSH, SecurityGroupName.HTTP, SecurityGroupName.HTTPS);

	public static final AwsAccount FOUNDATION = new AwsAccount.Builder("3627-3510-8948").accessKeyId("AKIAJFD5IM7IPVVUEBNA").keyName("kuali-key").securityGroups(DEFAULT_GROUPS)
			.build();

}
