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
package org.kuali.common.aws.ec2.model;

import static com.google.common.collect.Maps.newHashMap;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.Map;

public enum Regions {

	AP_NORTHEAST_1("ap-northeast-1", "Asia Pacific (Tokyo)"), //
	AP_SOUTHEAST_1("ap-southeast-1", "Asia Pacific (Singapore)"), //
	AP_SOUTHEAST_2("ap-southeast-2", "Asia Pacific (Sydney)"), //
	EU_WEST_1("eu-west-1", "EU (Ireland)"), //
	SA_EAST_1("sa-east-1", "South America (Sao Paulo)"), //
	US_EAST_1("us-east-1", "US East (Northern Virginia)"), //
	US_WEST_1("us-west-1", "US West (Northern California)"), //
	US_WEST_2("us-west-2", "US West (Oregon)"); //

	private final String name;
	private final String location;

	private Regions(String name, String location) {
		checkNotBlank(name, "name");
		checkNotBlank(location, "location");
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public static Map<String, Regions> asMap() {
		Map<String, Regions> map = newHashMap();
		for (Regions region : values()) {
			map.put(region.getName(), region);
		}
		return map;
	}

	/**
	 * http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/java-dg-region-selection.html
	 * 
	 * <p>
	 * The AWS Java SDK uses the US East (Northern Virginia) Region by default.
	 * </p>
	 * 
	 * <p>
	 * The AWS Management Console uses the US West (Oregon) Region as its default.
	 * </p>
	 * 
	 * <p>
	 * If you use both, be sure to always be explicit about the region you want.
	 * </p>
	 */
	public static final Regions DEFAULT_REGION = US_EAST_1;
}
