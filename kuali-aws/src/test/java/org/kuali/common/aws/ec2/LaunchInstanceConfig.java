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

import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchInstanceConfig {

	@Autowired
	EC2Service service;

	@Autowired
	EnvironmentService env;

	@Bean(initMethod = "execute")
	public Executable main() {
		String ami = env.getString("ec2.ami");
		String keyName = env.getString("ec2.key");
		String availabilityZone = env.getString("ec2.availabilityZone");
		InstanceType type = InstanceType.valueOf(env.getString("ec2.type"));
		List<Tag> tags = getTags();
		LaunchInstanceContext context = new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone).tags(tags).build();
		return new LaunchInstanceExecutable(service, context);
	}

	protected List<Tag> getTags() {
		String name = env.getString("ec2.tag.name");
		String value = env.getString("ec2.tag.value");
		Tag tag = new Tag(name, value);
		return Collections.singletonList(tag);
	}
}
