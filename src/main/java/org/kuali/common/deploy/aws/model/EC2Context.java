package org.kuali.common.deploy.aws.model;

import org.kuali.common.util.Assert;

public class EC2Context {

	public EC2Context(String ami, String securityGroup, String type) {
		Assert.noBlanks(ami, securityGroup, type);
		this.ami = ami;
		this.securityGroup = securityGroup;
		this.type = type;
	}

	private final String ami;
	private final String securityGroup;
	private final String type;

	public String getAmi() {
		return ami;
	}

	public String getSecurityGroup() {
		return securityGroup;
	}

	public String getType() {
		return type;
	}

}
