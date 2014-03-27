/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
