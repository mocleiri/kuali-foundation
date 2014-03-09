package org.kuali.common.aws.ec2.model;

public enum AMI {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	// amzn-ami-minimal-pv-2013.09.0.x86_64-ebs
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09(Distro.AMAZON, "2013.09", "ami-65792c0c", "amzn-ami-minimal-pv-2013.09.0.x86_64-ebs"), //
	UBUNTU_64_BIT_PRECISE_LTS(Distro.UBUNTU, "12.04", "ami-0b9c9f62", "ubuntu/images/ebs/ubuntu-precise-12.04-amd64-server-20140227");

	private final Distro distro;
	private final String version;
	private final String id;
	private final String code;

	private AMI(Distro distro, String version, String id, String code) {
		this.distro = distro;
		this.version = version;
		this.id = id;
		this.code = code;
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

	public String getCode() {
		return code;
	}

}
