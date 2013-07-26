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
package org.kuali.common.util.project.spring.config;

import java.util.Properties;

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectServiceConfig;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

@Configuration
@Maven
@Import({ ProjectServiceConfig.class })
public class MavenProjectConfig implements ProjectConfig {

	// Spring Maven Plugin wires this in for us
	@Autowired
	@Qualifier(MavenConstants.PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Autowired
	ProjectServiceConfig projectServiceConfig;

	@Override
	@Bean
	public Project project() {

		// Make sure the maven properties got wired in correctly
		Assert.notNull(mavenProperties, "mavenProperties are null");

		// Enhance the default set of Maven properties
		MavenUtils.augmentProjectProperties(projectServiceConfig.projectService(), mavenProperties);

		// Get a reference to the project service
		ProjectService service = projectServiceConfig.projectService();

		// Use the service to convert the properties into a Project
		return service.getProject(mavenProperties);
	}

}
