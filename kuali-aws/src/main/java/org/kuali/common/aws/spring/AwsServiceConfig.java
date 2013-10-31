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
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.kuali.common.util.wait.WaitService;
import org.kuali.common.util.wait.spring.WaitServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.google.common.base.Optional;

@Configuration
@Import({ SpringServiceConfig.class, WaitServiceConfig.class })
public class AwsServiceConfig {

	private static final String REGION_KEY = "aws.region";
	private static final String TIMEOFFSET_KEY = "aws.timeOffset";
	private static final String ENDPOINT_KEY = "aws.endpoint";

	@Autowired
	EnvironmentService env;

	@Autowired
	WaitService service;

	@Autowired
	AWSCredentials credentials;

	@Bean
	public EC2Service ec2Service() {
		Optional<Region> region = getRegion();
		Optional<String> endpoint = SpringUtils.getOptionalString(env, ENDPOINT_KEY);
		Optional<Integer> timeOffsetInSeconds = getTimeOffsetInSeconds();
		return new DefaultEC2Service.Builder(credentials, service).region(region.orNull()).endpoint(endpoint.orNull()).timeOffsetInSeconds(timeOffsetInSeconds.orNull()).build();
	}

	protected Optional<Integer> getTimeOffsetInSeconds() {
		Optional<String> offset = SpringUtils.getOptionalString(env, TIMEOFFSET_KEY);
		if (offset.isPresent()) {
			// Convert the text from the property into a millisecond value
			long millis = FormatUtils.getMillis(offset.get());
			// The unit of measure the Amazon EC2 client needs is seconds not milliseconds
			Long seconds = millis / 1000;
			// Return the seconds value as an integer
			return Optional.of(seconds.intValue());
		} else {
			return Optional.absent();
		}
	}

	protected Optional<Region> getRegion() {
		Optional<String> regionName = SpringUtils.getOptionalString(env, REGION_KEY);
		if (regionName.isPresent()) {
			return Optional.of(RegionUtils.getRegion(regionName.get()));
		} else {
			return Optional.absent();
		}
	}

}
