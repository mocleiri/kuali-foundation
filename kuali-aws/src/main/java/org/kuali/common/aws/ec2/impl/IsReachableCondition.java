package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.status.InstanceStatusName;
import org.kuali.common.aws.ec2.model.status.InstanceStatusType;
import org.kuali.common.aws.ec2.model.status.InstanceStatusValue;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

/**
 * This condition being met means that Amazon has:
 * 
 * <ol>
 * <li>Verified that the EC2 instance is connected to the network</li>
 * <li>Verified that the EC2 instance's operating system is accepting traffic</li>
 * <ol>
 */
public final class IsReachableCondition implements Condition {

	public IsReachableCondition(EC2Service service, String instanceId) {
		Assert.noBlanks(instanceId);
		Assert.noNulls(service);
		this.instanceId = instanceId;
		this.service = service;
	}

	private final EC2Service service;
	private final String instanceId;

	@Override
	public boolean isTrue() {
		boolean system = isReachable(InstanceStatusType.SYSTEM);
		boolean instance = isReachable(InstanceStatusType.INSTANCE);
		return system && instance;
	}

	/**
	 * Return true only if the "reachability" status equals "passed"
	 */
	protected boolean isReachable(InstanceStatusType type) {
		String value = service.getStatus(instanceId, type, InstanceStatusName.REACHABILITY.getValue());
		return InstanceStatusValue.PASSED.getValue().equals(value);
	}

	public EC2Service getService() {
		return service;
	}

	public String getInstanceId() {
		return instanceId;
	}

}
