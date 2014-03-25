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
import org.kuali.common.util.maven.spring.AutowiredMavenProperties;
import org.kuali.common.util.maven.spring.NoAutowiredMavenProperties;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

/**
 * You can use this configuration transparently between the Maven CLI and a normal runtime by injecting <code>projectId</code> into your context.
 * 
 * When running from the Maven CLI, <code>projectId</code> is ignored because project properties are pre-injected into the context by spring-maven-plugin.
 * 
 * When running in a normal runtime, <code>projectId</code> is used to load a <code>project.properties</code> file.
 * 
 * Project Id's are in this format:
 * 
 * <pre>
 *   org.kuali.common:kuali-util
 * </pre>
 * 
 * @deprecated
 */
@Configuration
@Deprecated
public class ProjectPropertySourceConfig extends BasicPropertySourceConfig {

	private static final String PROJECT_BEAN_NAME = "project.immutable";
	public static final String PROJECT_ID_BEAN_NAME = "project.id";

	@Autowired
	@Qualifier(PROJECT_BEAN_NAME)
	Project project;

	@Override
	protected Properties getOverrides() {
		return project.getProperties();
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Configuration
	@NoAutowiredMavenProperties
	@Import({ ProjectServiceConfig.class })
	static class RuntimeProjectConfig {

		// Use of this configuration at runtime requires "projectId" wired into the context
		// Format for "projectId" is [groupId:artifactId], eg "org.kuali.common:kuali-util"
		@Autowired
		@Qualifier(PROJECT_ID_BEAN_NAME)
		String projectId;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Bean(name = PROJECT_BEAN_NAME)
		public Project runtimeProjectConfigProject() {

			// Make sure projectId got wired in correctly
			Assert.hasText(projectId, "projectId is blank");

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Use the service to convert the projectId into a Project
			return service.getProject("", "");
		}
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Configuration
	@AutowiredMavenProperties
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

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Enhance the default set of Maven properties
			MavenUtils.augmentProjectProperties(service, mavenProperties);

			// Use the service to convert the properties into a Project
			return service.getProject(mavenProperties);
		}
	}

}
