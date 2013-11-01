package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateName;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

/**
 * Until this condition returns true, there is no point in attempting to do anything with an EC2 instance. After it returns true, Amazon has verified that the instance is online
 * and functioning.
 */
public final class IsOnlineCondition implements Condition {

	public IsOnlineCondition(EC2Service service, String instanceId) {
		Assert.noNulls(service);
		Assert.noBlanks(instanceId);
		this.running = new InstanceStateCondition(service, instanceId, InstanceStateName.RUNNING);
		this.reachable = new IsReachableCondition(service, instanceId);
	}

	private final Condition running;
	private final Condition reachable;

	@Override
	public boolean isTrue() {
		return running.isTrue() && reachable.isTrue();
	}

	public Condition getRunning() {
		return running;
	}

	public Condition getReachable() {
		return reachable;
	}

}
