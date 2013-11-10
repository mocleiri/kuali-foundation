package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public enum Distro {

	AMAZON_LINUX("amazon-linux"), //
	CENTOS("centos"), //
	UBUNTU("ubuntu"), //
	DEBIAN("debian"), //
	RED_HAT("redhat");

	private final String name;

	private Distro(String name) {
		Assert.noBlanks(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
