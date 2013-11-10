package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public enum AMI {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	// amzn-ami-minimal-pv-2013.09.0.x86_64-ebs
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09("amazon-linux", "2013.09", "ami-65792c0c");

	private final String distro;
	private final String version;
	private final String id;

	private AMI(String distro, String version, String id) {
		Assert.noBlanks(distro, version, id);
		this.distro = distro;
		this.version = version;
		this.id = id;
	}

	public String getDistro() {
		return distro;
	}

	public String getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}

}
