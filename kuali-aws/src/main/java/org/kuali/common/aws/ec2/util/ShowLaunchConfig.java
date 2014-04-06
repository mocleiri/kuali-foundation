/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.ec2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.Regions;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class ShowLaunchConfig implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowLaunchConfig.class);

	private final EC2ServiceContext serviceContext;
	private final LaunchInstanceContext instanceContext;
	private final boolean skip;

	public ShowLaunchConfig(EC2ServiceContext serviceContext, LaunchInstanceContext instanceContext) {
		this(serviceContext, instanceContext, false);
	}

	public ShowLaunchConfig(EC2ServiceContext serviceContext, LaunchInstanceContext instanceContext, boolean skip) {
		Assert.noNulls(serviceContext, instanceContext);
		this.serviceContext = serviceContext;
		this.instanceContext = instanceContext;
		this.skip = skip;
	}

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		String accessKey = serviceContext.getCredentials().getAWSAccessKeyId();
		String regionName = getRegionName(serviceContext);
		String regionLocation = getRegionLocation(serviceContext);
		String availabilityZone = getAvailabilityZone(instanceContext);

		logger.info("---------- Launching EC2 Instance ----------");
		logger.info("AWS Access Key: {}", accessKey);
		logger.info("Location: {}", regionLocation);
		logger.info("Region: {}", regionName);
		logger.info("Zone: {}", availabilityZone);
		logger.info("AMI: {}", instanceContext.getAmi());
		logger.info("Type: {}", instanceContext.getType().toString());
		logger.info("Key: {}", instanceContext.getKeyPair().getName());
		logger.info("Security Groups: {}", CollectionUtils.asCSV(getSecurityGroups(instanceContext)));
		logger.info("--------------------------------------------");
	}

	protected List<String> getSecurityGroups(LaunchInstanceContext context) {
		List<String> names = new ArrayList<String>();
		for (KualiSecurityGroup group : context.getSecurityGroups()) {
			names.add(group.getName());
		}
		Collections.sort(names);
		return ImmutableList.copyOf(names);
	}

	protected String getAvailabilityZone(LaunchInstanceContext context) {
		Optional<String> zone = context.getAvailabilityZone();
		if (!zone.isPresent()) {
			return "no preference";
		} else {
			return zone.get();
		}
	}

	protected String getRegionLocation(EC2ServiceContext context) {
		Map<String, Regions> map = Regions.asMap();
		Regions region = map.get(context.getRegion());
		if (region == null) {
			// They've supplied a region we don't know about
			return "unknown";
		} else {
			return region.getLocation();
		}
	}

	protected String getRegionName(EC2ServiceContext context) {
		return context.getRegion();
	}

	public EC2ServiceContext getServiceContext() {
		return serviceContext;
	}

	public LaunchInstanceContext getInstanceContext() {
		return instanceContext;
	}

	public boolean isSkip() {
		return skip;
	}

}
