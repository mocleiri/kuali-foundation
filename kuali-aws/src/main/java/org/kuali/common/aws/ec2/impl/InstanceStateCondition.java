package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateName;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;

/**
 * This condition being met means that the corresponding EC2 instance has reached <code>targetState</code>
 */
public final class InstanceStateCondition implements Condition {

	public InstanceStateCondition(EC2Service service, String instanceId, InstanceStateName targetState) {
		Assert.noNulls(service, targetState);
		Assert.noBlanks(instanceId);
		this.instanceId = instanceId;
		this.service = service;
		this.targetState = targetState;
	}

	private final EC2Service service;
	private final String instanceId;
	private final InstanceStateName targetState;

	@Override
	public boolean isTrue() {
		Instance instance = service.getInstance(instanceId);
		InstanceState instanceState = instance.getState();
		String stateName = instanceState.getName();
		return targetState.getValue().equals(stateName);
	}

	public EC2Service getService() {
		return service;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public InstanceStateName getTargetState() {
		return targetState;
	}

}
