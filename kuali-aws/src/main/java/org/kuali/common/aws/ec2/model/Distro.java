package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public enum Distro {

	AMAZON("amazon"), //
	CENTOS("centos"), //
	UBUNTU("ubuntu"), //
	DEBIAN("debian"), //
	REDHAT("redhat"); //

	private final String name;

	private Distro(String name) {
		Assert.noBlanks(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
