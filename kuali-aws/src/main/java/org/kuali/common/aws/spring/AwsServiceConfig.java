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
package org.kuali.common.aws.spring;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.kuali.common.util.wait.WaitService;
import org.kuali.common.util.wait.spring.WaitServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.google.common.base.Optional;

@Configuration
@Import({ SpringServiceConfig.class, WaitServiceConfig.class })
public class AwsServiceConfig {

	private static final String ACCESS_KEY = "aws.accessKeyId";
	private static final String SECRET_KEY = "aws.secretKey";
	private static final String REGION_KEY = "aws.region";

	@Autowired
	EnvironmentService env;

	@Autowired
	WaitService service;

	@Bean
	public EC2Service ec2Service() {
		String accessKey = env.getString(ACCESS_KEY);
		String secretKey = env.getString(SECRET_KEY);
		Optional<Region> region = getRegion();
		if (region.isPresent()) {
			return new DefaultEC2Service.Builder(accessKey, secretKey, service).region(region.get()).build();
		} else {
			return new DefaultEC2Service.Builder(accessKey, secretKey, service).build();
		}
	}

	protected Optional<Region> getRegion() {
		String regionName = env.getString(REGION_KEY, NullUtils.NONE);
		if (NullUtils.isNullOrNone(regionName)) {
			return Optional.<Region> absent();
		} else {
			Regions regions = Regions.fromName(regionName);
			Region region = RegionUtils.getRegion(regions.getName());
			return Optional.of(region);
		}
	}

}
