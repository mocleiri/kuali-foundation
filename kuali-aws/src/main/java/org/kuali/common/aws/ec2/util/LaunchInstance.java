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
package org.kuali.common.aws.ec2.util;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class LaunchInstance implements Executable {

	private final EC2Service service;
	private final LaunchInstanceContext context;
	private final boolean skip;

	public LaunchInstance(EC2Service service, LaunchInstanceContext context) {
		this(service, context, false);
	}

	public LaunchInstance(EC2Service service, LaunchInstanceContext context, boolean skip) {
		Assert.noNulls(service, context);
		this.service = service;
		this.context = context;
		this.skip = skip;
	}

	@Override
	public void execute() {
		if (!skip) {
			service.launchInstance(context);
		}
	}

	public EC2Service getService() {
		return service;
	}

	public LaunchInstanceContext getContext() {
		return context;
	}

}
