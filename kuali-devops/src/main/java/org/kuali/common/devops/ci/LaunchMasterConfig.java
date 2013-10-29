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

import java.util.Arrays;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.spring.AwsServiceConfig;
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

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchMasterConfig {

	private static final String KEY_NAME = "kuali-key";
	private static final String US_EAST_1D = "us-east-1d";

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

	private static final LaunchInstanceContext getDefaultMasterContext() {
		List<String> securityGroups = Arrays.asList("ssh", "http");
		List<Tag> tags = Arrays.asList(new Tag("", ""));
		RootVolume rootVolume = new RootVolume(25);
		return new LaunchInstanceContext.Builder("ami-65792c0c", "kuali-key").type(InstanceType.M1Large).availabilityZone(US_EAST_1D).tags(tags).securityGroups(securityGroups)
				.preventTermination(true).rootVolume(rootVolume).build();
	}

	protected LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env) {
		String ami = env.getString(LaunchUtils.AMI_KEY, "ami-65792c0c");
		String keyName = env.getString(LaunchUtils.KEY_NAME_KEY, "kuali-key");
		String availabilityZone = env.getString(LaunchUtils.AVAILABILITY_ZONE_KEY, "us-east-1d");
		InstanceType type = InstanceType.fromValue(env.getString(LaunchUtils.TYPE_KEY, InstanceType.M1Large.toString()));
		List<Tag> tags = LaunchUtils.getTags(env);
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, LaunchUtils.SECURITY_GROUPS_KEY, "ci");
		int sizeInGigabytes = env.getInteger(LaunchUtils.ROOT_VOLUME_SIZE_KEY, 25);
		RootVolume rootVolume = new RootVolume(sizeInGigabytes);
		boolean preventTermination = env.getBoolean(LaunchUtils.PREVENT_TERMINATION_KEY, true);
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone.get()).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume).timeoutMillis(timeoutMillis).ebsOptimized(ebsOptimized).enableMonitoring(enableMonitoring).build();
	}

}
