package org.kuali.common.aws.ec2.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public enum Distro {

	AMAZON("amazon"), //
	CENTOS("centos"), //
	UBUNTU("ubuntu"), //
	DEBIAN("debian"), //
	REDHAT("redhat"); //

	private final String name;

	private Distro(String name) {
		checkNotBlank(name, "name");
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
