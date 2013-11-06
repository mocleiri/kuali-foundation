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

import java.util.List;

import org.kuali.common.aws.KeyPairs;
import org.kuali.common.aws.SecurityGroups;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.SetPermissionsResult;
import org.kuali.common.aws.model.KeyPair;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

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
		KeyPair keyPair = KeyPairs.FOUNDATION.getKeyPair();
		boolean exists = service.isExistingKeyPair(keyPair.getName());
		boolean valid = service.isValidKeyPair(keyPair);
		logger.info("exists: {}  valid: {}", exists, valid);
		List<String> names = service.getSecurityGroupNames();
		KualiSecurityGroup ci = SecurityGroups.CI.getGroup();
		KualiSecurityGroup master = SecurityGroups.CI_MASTER.getGroup();
		KualiSecurityGroup slave = SecurityGroups.CI_BUILD_SLAVE.getGroup();
		handleGroups(names, ci, master, slave);
		return null;
	}

	protected void handleGroups(List<String> names, KualiSecurityGroup... groups) {
		List<KualiSecurityGroup> list = ImmutableList.copyOf(groups);
		for (KualiSecurityGroup element : list) {
			if (!names.contains(element.getName())) {
				service.createSecurityGroup(element);
			}
			SetPermissionsResult result = service.setPermissions(element.getName(), element.getPermissions());
			Object[] args = { result.getAdds().size(), result.getDeletes().size(), result.getExisting().size() };
			logger.info("adds: {}  deletes: {}  existing: {}", args);
		}
	}

}
