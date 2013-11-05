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
package org.kuali.common.aws.ec2;

import org.kuali.common.aws.SecurityGroups;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.SetPermissionsResult;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class, FoundationCredentialsConfig.class })
public class InvokeEC2ServiceConfig {

	private static final Logger logger = LoggerFactory.getLogger(InvokeEC2ServiceConfig.class);

	@Autowired
	EC2Service service;

	@Autowired
	EC2ServiceContext context;

	@Autowired
	EnvironmentService env;

	@Bean
	public Object invokeEC2Service() {
		KualiSecurityGroup group = SecurityGroups.CI.getGroup();
		boolean exists = service.isExistingSecurityGroup(group.getName());
		if (!exists) {
			service.createSecurityGroup(group);
		}
		SetPermissionsResult result = service.setPermissions(group.getName(), group.getPermissions());
		Object[] args = { result.getAdds().size(), result.getDeletes().size(), result.getExisting().size() };
		logger.info("adds: {}  deletes: {}  existing: {}", args);

		return null;
	}

}
