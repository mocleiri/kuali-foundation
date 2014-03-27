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
