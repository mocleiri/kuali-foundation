package org.kuali.common.aws.ec2.model.security;

public enum SecurityGroups {

	SSH()
	
	private final ImmutableSecurityGroup securityGroup;

	private SecurityGroups(ImmutableSecurityGroup securityGroup) {
		this.securityGroup = securityGroup;
	}

	public ImmutableSecurityGroup getSecurityGroup() {
		return securityGroup;
	}

}
