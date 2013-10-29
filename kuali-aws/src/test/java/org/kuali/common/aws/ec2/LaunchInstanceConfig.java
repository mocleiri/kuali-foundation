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
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.util.Str;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AwsServiceConfig.class, SpringServiceConfig.class })
public class LaunchInstanceConfig {

	@Autowired
	EC2Service service;

	@Autowired
	EnvironmentService env;

	@Bean
	public Object launchAndThenTerminate() {
		LaunchInstanceContext context = getLaunchInstanceContext();
		Instance instance = service.launchInstance(context);
		// service.terminateInstance(instance.getInstanceId());
		return null;
	}

	public Object launchAndThenTerminate2() {
		DefaultEC2Service des = (DefaultEC2Service) service;
		Image image = des.getAmi("ami-65792c0c");
		List<BlockDeviceMapping> mappings = image.getBlockDeviceMappings();
		System.out.println(image.getImageId());
		System.out.println(image.getArchitecture());
		System.out.println(image.getRootDeviceName());
		System.out.println(mappings.size());
		showBlockDeviceMapping(mappings.get(0));
		// LaunchInstanceContext context = getLaunchInstanceContext();
		// Instance instance = service.launchInstance(context);
		// service.terminateInstance(instance.getInstanceId());
		return null;
	}

	protected void showBlockDeviceMapping(BlockDeviceMapping mapping) {
		EbsBlockDevice device = mapping.getEbs();
		System.out.println(mapping.getDeviceName());
		System.out.println(device.getSnapshotId());
		System.out.println(device.getDeleteOnTermination());
		System.out.println(device.getVolumeSize());
	}

	protected LaunchInstanceContext getLaunchInstanceContext() {
		String ami = env.getString("ec2.ami");
		String keyName = env.getString("ec2.keyName");
		String availabilityZone = env.getString("ec2.availabilityZone", NullUtils.NONE);
		InstanceType type = InstanceType.fromValue(env.getString("ec2.type"));
		List<Tag> tags = getTags();
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, "ec2.securityGroups");
		RootVolume rootVolume = getRootVolume();
		boolean preventTermination = env.getBoolean("ec2.preventTermination", false);
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume).build();
	}

	protected RootVolume getRootVolume() {
		Optional<Integer> sizeInGigabytes = SpringUtils.getOptionalInteger(env, "ec2.rootVolume.sizeInGigabytes");
		boolean deleteOnTermination = env.getBoolean("ec2.rootVolume.deleteOnTermination", RootVolume.DEFAULT_DELETE_ON_TERMINATION);
		return new RootVolume(sizeInGigabytes, deleteOnTermination);
	}

	protected List<Tag> getTags() {
		List<String> list = SpringUtils.getNoneSensitiveListFromCSV(env, "ec2.tags", NullUtils.NONE);
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
