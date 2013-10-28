package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.Reachability;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

/**
 * This condition being met means that AWS has:
 * 
 * <ol>
 * <li>Verified that the EC2 instance is connected to the network</li>
 * <li>Verified that the EC2 instance's operating system is accepting traffic</li>
 * <ol>
 */
public final class IsOnlineCondition implements Condition {

	public IsOnlineCondition(EC2Service service, String instanceId) {
		Assert.noBlanks(instanceId);
		Assert.noNulls(service);
		this.instanceId = instanceId;
		this.service = service;
	}

	private final EC2Service service;
	private final String instanceId;

	@Override
	public boolean isTrue() {
		Reachability reachability = service.getReachability(instanceId);
		boolean system = Reachability.STATUS_PASSED.equals(reachability.getSystem());
		boolean instance = Reachability.STATUS_PASSED.equals(reachability.getInstance());
		return system && instance;
	}

	public EC2Service getService() {
		return service;
	}

	public String getInstanceId() {
		return instanceId;
	}

}
