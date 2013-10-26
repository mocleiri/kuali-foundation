package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateEnum;
import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;

public final class HealthyInstanceCondition implements Condition {

	public HealthyInstanceCondition(EC2Service service, String instanceId, InstanceStateEnum state) {
		Assert.noNulls(service, state);
		Assert.noBlanks(instanceId);
		this.instanceId = instanceId;
		this.service = service;
		this.state = state;
	}

	private final EC2Service service;
	private final String instanceId;
	private final InstanceStateEnum state;

	@Override
	public boolean isTrue() {
		Instance instance = service.getInstance(instanceId);
		InstanceState currentInstanceState = instance.getState();
		String currentState = currentInstanceState.getName();
		return state.getValue().equals(currentState);
	}

	public EC2Service getService() {
		return service;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public InstanceStateEnum getState() {
		return state;
	}

}
