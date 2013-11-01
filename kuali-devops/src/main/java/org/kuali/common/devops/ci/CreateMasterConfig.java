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
import org.kuali.common.aws.spring.AwsCredentialsConfig;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.devops.aws.Accounts;
import org.kuali.common.devops.aws.AwsConstants;
import org.kuali.common.devops.aws.SecurityGroupName;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class CreateMasterConfig implements AwsCredentialsConfig {

	private static final int TWENTY_FIVE_GIGABYTES = 25;

	@Autowired
	EC2Service service;

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public AWSCredentials awsCredentials() {
		return Accounts.FOUNDATION.getAccount().getCredentials().get();
	}

	@Bean
	public Object launchAndThenTerminate() {
		LaunchInstanceContext context = LaunchUtils.getContext(env, jenkinsMaster());
		Instance instance = service.launchInstance(context);
		System.out.println(instance.getPublicDnsName());
		return null;
	}

	@Bean
	public LaunchInstanceContext jenkinsMaster() {
		AwsAccount account = Accounts.FOUNDATION.getAccount();
		String ami = AwsConstants.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09;
		String keyName = account.getKey().get().getName();
		InstanceType type = InstanceType.M1Large;
		String zone = AwsConstants.US_EAST_1D;
		List<String> securityGroups = SecurityGroupName.getValues(SecurityGroupName.SSH, SecurityGroupName.HTTP, SecurityGroupName.HTTPS);
		List<Tag> tags = getTags();
		RootVolume rootVolume = new RootVolume(TWENTY_FIVE_GIGABYTES);
		boolean preventTermination = true;
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(zone).tags(tags).securityGroups(securityGroups).preventTermination(preventTermination)
				.rootVolume(rootVolume).build();
	}

	protected List<Tag> getTags() {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(Tags.Name.MASTER.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Stack.PRODUCTION.getTag());
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		return ImmutableList.copyOf(tags);
	}

}
