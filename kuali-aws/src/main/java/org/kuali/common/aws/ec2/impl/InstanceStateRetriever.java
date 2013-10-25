package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.util.Assert;

import com.amazonaws.services.ec2.model.Instance;

public class InstanceStateRetriever implements StateRetriever {

	private final String instanceId;
	private final EC2Service service;

	public InstanceStateRetriever(EC2Service service, String instanceId) {
		Assert.noNulls(service);
		Assert.noBlanks(instanceId);
		this.service = service;
		this.instanceId = instanceId;
	}

	@Override
	public String getState() {
		Instance instance = service.getInstance(instanceId);
		String state = instance.getState().getName();
		return state;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public EC2Service getService() {
		return service;
	}

}
