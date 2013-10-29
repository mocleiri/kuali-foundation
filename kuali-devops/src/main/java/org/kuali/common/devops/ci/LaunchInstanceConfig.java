/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.devops.ci;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.ec2.model.Instance;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchInstanceConfig {

	@Autowired
	EC2Service service;

	@Autowired
	EnvironmentService env;

	@Bean
	public Object launchAndThenTerminate() {
		LaunchInstanceContext context = LaunchUtils.getLaunchInstanceContext(env);
		Instance instance = service.launchInstance(context);
		// service.terminateInstance(instance.getInstanceId());
		return null;
	}

}
