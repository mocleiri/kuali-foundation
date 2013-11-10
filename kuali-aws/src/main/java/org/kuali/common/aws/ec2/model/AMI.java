package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public enum AMI {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	// amzn-ami-minimal-pv-2013.09.0.x86_64-ebs
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09(Distro.AMAZON_LINUX, "2013.09", "ami-65792c0c");

	private final Distro distro;
	private final String version;
	private final String id;

	private AMI(Distro distro, String version, String id) {
		Assert.noBlanks(version, id);
		Assert.noNulls(distro);
		this.distro = distro;
		this.version = version;
		this.id = id;
	}

	public Distro getDistro() {
		return distro;
	}

	public String getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}

}
