package org.kuali.common.devops.ci.model;

import static com.google.common.base.Optional.absent;

import org.kuali.common.aws.ec2.model.AMI;
import org.kuali.common.aws.ec2.model.Distro;
import org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping;
import org.kuali.common.aws.ec2.model.ImmutableEbsBlockDevice;

import com.google.common.base.Optional;

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

	private static final String SSD_DEVICE_NAME = "/dev/sdb";
	private static final String SSD_VIRTUAL_NAME = "ephemeral0";
	private static final Optional<ImmutableEbsBlockDevice> NO_EBS = absent();
	private static final Optional<String> ABSENT = absent();

	public static final ImmutableBlockDeviceMapping SSD = new ImmutableBlockDeviceMapping(SSD_DEVICE_NAME, NO_EBS, Optional.of(SSD_VIRTUAL_NAME), ABSENT);

}
