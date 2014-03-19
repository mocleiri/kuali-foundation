package org.kuali.common.devops.ci.model;

import org.kuali.common.aws.ec2.model.AMI;
import org.kuali.common.aws.ec2.model.Distro;

public final class Constants {

	private Constants() {
	}

	public static final String GPG_PASSPHRASE_ENCRYPTED = "coSLMPP2IsSAXYVp9NIsvxzqAkd7N+Yh";
	public static final String AMAZON_ACCOUNT = "foundation";
	public static final String DOMAIN = "kuali.org";
	public static final Distro DISTRO = Distro.UBUNTU;
	public static final String DISTRO_VERSION = "12.04";
	public static final String ROOT = "root";
	public static final String UBUNTU = "ubuntu";
	public static final AMI DEFAULT_AMI = AMI.UBUNTU_64_BIT_PRECISE_LTS_1204_US_WEST;

}
