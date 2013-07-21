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
package org.kuali.common.util.config.spring;

import java.util.Properties;

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectServiceConfig;
import org.kuali.common.util.spring.config.SpringConfigConstants;
import org.kuali.common.util.spring.config.annotation.Default;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

/**
 * 
 */
@Configuration
public class ProjectPropertySourceConfig extends BasicPropertySourceConfig {

	private static final String PROJECT_BEAN_NAME = "project.immutable";

	@Autowired
	@Qualifier(PROJECT_BEAN_NAME)
	Project project;

	@Override
	protected Properties getOverrides() {
		return project.getProperties();
	}

	@Configuration
	@Default
	@Import({ ProjectServiceConfig.class })
	static class RuntimeProjectConfig {

		// Use of this configuration at runtime requires "projectId" wired into the context
		// Format for "projectId" is [groupId:artifactId], eg "org.kuali.common:kuali-util"
		@Autowired
		@Qualifier(SpringConfigConstants.PROJECT_ID_BEAN_NAME)
		String projectId;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Bean(name = PROJECT_BEAN_NAME)
		public Project runtimeProjectConfigProject() {

			// Make sure projectId got wired in correctly
			Assert.hasText(projectId, "projectId is blank");

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Load project.properties to create a Project object
			return service.getProject(projectId);
		}
	}

	@Configuration
	@Maven
	@Import({ ProjectServiceConfig.class })
	static class BuildProjectConfig {

		// Spring Maven Plugin wires this in for us
		@Autowired
		@Qualifier(MavenConstants.PROPERTIES_BEAN_NAME)
		Properties mavenProperties;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Bean(name = PROJECT_BEAN_NAME)
		public Project buildProjectConfigProject() {
			// Make sure the maven properties got wired in correctly
			Assert.notNull(mavenProperties, "mavenProperties are null");

			// Add in org, group, home, and enhanced version properties
			MavenUtils.augmentProjectProperties(mavenProperties);

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Get an immutable project from the properties
			return service.getProject(mavenProperties);
		}
	}

}
