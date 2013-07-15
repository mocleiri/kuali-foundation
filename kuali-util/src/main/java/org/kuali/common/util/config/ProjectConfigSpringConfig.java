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
package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectConfigSpringConfig {

	private static final String SERVICE_KEY = "project.config.service";
	private static final String IDS_KEY = "project.config.ids";

	@Autowired
	Environment env;

	@Bean
	public ProjectConfigService utilProjectConfigService() {
		return SpringUtils.getInstance(env, SERVICE_KEY, DefaultProjectConfigService.class);
	}

	@Bean
	public List<Location> utilProjectConfigLocations() {
		ProjectConfigService service = utilProjectConfigService();

		List<String> ids = SpringUtils.getNoneSensitiveListFromCSV(env, IDS_KEY, Constants.NONE);

		List<Location> locations = new ArrayList<Location>();
		for (String id : ids) {
			List<Location> list = service.getLocations(id);
			locations.addAll(list);
		}
		return locations;
	}
}
