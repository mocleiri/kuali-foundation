package org.kuali.common.deploy.env.model;

import org.kuali.common.util.Assert;

public final class DeployEnvironment {

	public DeployEnvironment(String groupId, int sequence, String name) {
		Assert.noBlanks(groupId, name);
		Assert.isTrue(sequence > 0, "sequence must be a positive integer");
		this.sequence = sequence;
		this.groupId = groupId;
		this.name = name;
	}

	private final int sequence;
	private final String groupId;
	private final String name;

	public int getSequence() {
		return sequence;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getName() {
		return name;
	}

}
