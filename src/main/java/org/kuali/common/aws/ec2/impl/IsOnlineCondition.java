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
