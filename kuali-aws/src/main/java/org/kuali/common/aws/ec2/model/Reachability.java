package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public final class Reachability {

	public static final String REACHABILITY = "reachability";

	public Reachability(String system, String instance) {
		Assert.noBlanks(system, instance);
		this.system = system;
		this.instance = instance;
	}

	/**
	 * This string indicates the ability of AWS to get network packets to the instance. If this check fails, there may be an issue with the infrastructure hosting the instance
	 * (such as AWS power, networking or software systems). The instance may need to be restarted or replaced or it may require waiting for AWS systems to resolve an issue. This
	 * check does not validate that the operating system and applications are accepting traffic.
	 */
	private final String system;

	/**
	 * This string indicates the ability of the instance's operating system is accepting traffic. If this check fails, the instance may need to be rebooted or have modifications
	 * made to the operating system configuration.
	 */
	private final String instance;

	public String getSystem() {
		return system;
	}

	public String getInstance() {
		return instance;
	}

}
