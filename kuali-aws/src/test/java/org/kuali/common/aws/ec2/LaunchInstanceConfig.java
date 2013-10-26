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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.Str;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.main.spring.MainConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchInstanceConfig implements MainConfig {

	@Autowired
	EC2Service service;

	@Autowired
	EnvironmentService env;

	@Override
	@Bean(initMethod = "execute")
	public Executable main() {
		LaunchInstanceContext context = getLaunchInstanceContext();
		return new LaunchInstanceExecutable(service, context);
	}

	protected LaunchInstanceContext getLaunchInstanceContext() {
		String ami = env.getString("ec2.ami");
		String keyName = env.getString("ec2.keyName");
		String availabilityZone = env.getString("ec2.availabilityZone");
		InstanceType type = InstanceType.fromValue(env.getString("ec2.type"));
		List<Tag> tags = getTags();
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, "ec2.securityGroups");
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone).tags(tags).securityGroups(securityGroups).build();
	}

	protected List<Tag> getTags() {
		List<String> list = SpringUtils.getNoneSensitiveListFromCSV(env, "ec2.tags");
		List<Tag> tags = new ArrayList<Tag>();
		for (String element : list) {
			String[] tokens = Str.splitAndTrim(element, "=");
			Assert.isTrue(tokens.length == 2, "Expected exactly 2 tokens");
			String key = tokens[0];
			String value = tokens[1];
			Tag tag = new Tag(key, value);
			tags.add(tag);
		}
		return ImmutableList.copyOf(tags);
	}
}
