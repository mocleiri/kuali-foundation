package org.kuali.common.aws.ec2.model;

public enum AMI {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	// amzn-ami-minimal-pv-2013.09.0.x86_64-ebs
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09("amazon-linux", "2013.09", "ami-65792c0c");

	private final String id;
	private final String name;
	private final String version;

	private AMI(String name, String version, String id) {
		this.name = name;
		this.version = version;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
