package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public final class Reachability {

	public Reachability(String system, String instance) {
		Assert.noBlanks(system, instance);
		this.system = system;
		this.instance = instance;
	}

	private final String system;
	private final String instance;

	public String getSystem() {
		return system;
	}

	public String getInstance() {
		return instance;
	}

}
