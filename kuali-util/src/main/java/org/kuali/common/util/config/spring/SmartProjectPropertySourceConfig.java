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

import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.FullImmutableProject;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.spring.config.SpringConfigConstants;
import org.kuali.common.util.spring.config.annotation.Default;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * 
 */
@Configuration
public class SmartProjectPropertySourceConfig extends BasicPropertySourceConfig {

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
	static class RuntimeProjectConfig {

		@Autowired
		@Qualifier(SpringConfigConstants.PROJECT_ID_BEAN_NAME)
		String projectId;

		@Bean(name = PROJECT_BEAN_NAME)
		public Project immutableProject() {

			// Make sure projectId got wired in correctly
			Assert.hasText(projectId, "projectId is blank");

			// Load project.properties from disk
			Properties properties = ProjectUtils.loadProject(projectId).getProperties();

			// Get an immutable project from the properties
			return getImmutableProject(properties);
		}

	}

	@Configuration
	@Maven
	static class BuildProjectConfig {

		@Autowired
		@Qualifier(MavenConstants.MAVEN_PROPERTIES_BEAN_NAME)
		Properties mavenProperties;

		@Bean(name = PROJECT_BEAN_NAME)
		public Project immutableProject() {
			// Make sure the maven properties got wired in correctly
			Assert.notNull(mavenProperties, "mavenProperties are null");

			// Add in org, group, home, and enhanced version properties
			MavenUtils.augmentProjectProperties(mavenProperties);

			// Get an immutable project from the properties
			return getImmutableProject(mavenProperties);
		}
	}

	protected static FullImmutableProject getImmutableProject(Properties properties) {
		String groupId = properties.getProperty("project.groupId");
		String artifactId = properties.getProperty("project.artifactId");
		String version = properties.getProperty("project.version");
		return new FullImmutableProject(groupId, artifactId, version, properties);

	}

}
