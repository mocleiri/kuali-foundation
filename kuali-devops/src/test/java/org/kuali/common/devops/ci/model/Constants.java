package org.kuali.common.devops.ci.model;

import org.kuali.common.aws.ec2.model.Distro;

public final class Constants {

	private Constants() {
	}

	public static final String GPG_PASSPHRASE_ENCRYPTED = "VRK9Vsz1WwXtbrAEaSKSTa/Vf56mrrkDDcsExJMoKTM=";
	public static final String AMAZON_ACCOUNT = "foundation";
	public static final String DOMAIN = "kuali.org";
	public static final Distro DISTRO = Distro.UBUNTU;
	public static final String DISTRO_VERSION = "12.04";
	public static final String ROOT = "root";
	public static final String UBUNTU = "ubuntu";
	public static final String JENKINS_VERSION = "1.532.3";

}
