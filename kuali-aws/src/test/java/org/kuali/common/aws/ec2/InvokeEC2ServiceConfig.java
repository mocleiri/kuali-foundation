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
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.model.AMIs;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class, FoundationCredentialsConfig.class })
public class InvokeEC2ServiceConfig {

	@Autowired
	EC2Service service;

	@Autowired
	EC2ServiceContext context;

	@Autowired
	EnvironmentService env;

	@Bean
	public Object invokeEC2Service() {
		KeyPair keyPair = new KeyPair("kuali-devops-test-key22", KeyPairs.FOUNDATION.getKeyPair().getPublicKey());
		List<KualiSecurityGroup> groups = getSecurityGroups();
		String ami = AMIs.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09.getId();
		List<Tag> tags = ImmutableList.of(new Tag("Name", "ci.testing"));
		LaunchInstanceContext context = new LaunchInstanceContext.Builder(ami, keyPair).securityGroups(groups).tags(tags).build();
		service.launchInstance(context);
		return null;
	}

	protected List<KualiSecurityGroup> getSecurityGroups() {
		KualiSecurityGroup ci = SecurityGroups.CI.getGroup();
		KualiSecurityGroup master = SecurityGroups.CI_MASTER.getGroup();
		// KualiSecurityGroup slave = SecurityGroups.CI_BUILD_SLAVE.getGroup();
		return ImmutableList.of(ci, master);
	}

}
