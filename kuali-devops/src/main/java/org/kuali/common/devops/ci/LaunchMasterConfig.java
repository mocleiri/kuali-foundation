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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.devops.aws.DevOpsAwsConstants;
import org.kuali.common.devops.aws.SecurityGroupName;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchMasterConfig {

	private static final int DEFAULT_ROOT_VOLUME_SIZE_IN_GIGABYTES = 25;
	private static final boolean DEFAULT_PREVENT_TERMINATION = true;
	private static final List<String> DEFAULT_SECURITY_GROUPS = SecurityGroupName.getValues(SecurityGroupName.SSH, SecurityGroupName.HTTP, SecurityGroupName.HTTPS);
	private static final List<Tag> DEFAULT_MASTER_TAGS = getDefaultMasterTags();

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

	private LaunchInstanceContext getDefaultMasterLaunchContext() {
		AwsAccount account = DevOpsAwsConstants.FOUNDATION;
		String ami = env.getString(LaunchUtils.AMI_KEY, DevOpsAwsConstants.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09);
		String keyName = env.getString(LaunchUtils.KEY_NAME_KEY, account.getKeyName());
		InstanceType type = LaunchUtils.getType(env, InstanceType.M1Large);
		String zone = env.getString(LaunchUtils.AVAILABILITY_ZONE_KEY, DevOpsAwsConstants.US_EAST_1D);
		List<String> securityGroups = SpringUtils.getStrings(env, LaunchUtils.SECURITY_GROUPS_KEY, DEFAULT_SECURITY_GROUPS);
		List<Tag> tags = LaunchUtils.getTags(env, DEFAULT_MASTER_TAGS);
		RootVolume rootVolume = new RootVolume(DEFAULT_ROOT_VOLUME_SIZE_IN_GIGABYTES);
		boolean preventTermination = env.getBoolean(LaunchUtils.PREVENT_TERMINATION_KEY, DEFAULT_PREVENT_TERMINATION);
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(zone).tags(tags).securityGroups(securityGroups).preventTermination(preventTermination)
				.rootVolume(rootVolume).build();
	}

	private static final List<Tag> getDefaultMasterTags() {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag("Name", "ci-test-server-ok-to-delete"));
		tags.add(new Tag("Vendor", "jenkins"));
		tags.add(new Tag("Stack", "production"));
		tags.add(new Tag("Team", "devops"));
		tags.add(new Tag("Project", "shared"));
		return ImmutableList.copyOf(tags);
	}

}
