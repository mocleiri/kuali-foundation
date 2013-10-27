package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.Reachability;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

public final class ReachabilityCondition implements Condition {

	public ReachabilityCondition(EC2Service service, String instanceId, Reachability targetReachability) {
		Assert.noNulls(service, targetReachability);
		Assert.noBlanks(instanceId);
		this.instanceId = instanceId;
		this.service = service;
		this.targetReachability = targetReachability;
	}

	private final EC2Service service;
	private final String instanceId;
	private final Reachability targetReachability;

	@Override
	public boolean isTrue() {
		Reachability reachability = service.getReachability(instanceId);
		boolean system = targetReachability.getSystem().equals(reachability.getSystem());
		boolean instance = targetReachability.getInstance().equals(reachability.getInstance());
		return system && instance;
	}

	public EC2Service getService() {
		return service;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public Reachability getTargetReachability() {
		return targetReachability;
	}

}
