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
package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DeployProjectConfig.class)
public class DeployPropertiesConfig {

	@Autowired
	DeployProjectConfig projectConfig;

	@Bean
	public ProjectProperties deployProjectProperties() {
		Project project = projectConfig.deployProject();

		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/deploy/deploy.properties");
		locations.add("classpath:org/kuali/common/deploy/driver.properties");
		locations.add("classpath:${project.groupId.path}/deploy.properties");
		locations.add("classpath:${project.groupId.path}/${project.artifactId}.properties");
		locations.add("classpath:${project.groupId.path}/env${deploy.env}.properties");

		return ConfigUtils.getProjectProperties(project, locations);
	}

}
