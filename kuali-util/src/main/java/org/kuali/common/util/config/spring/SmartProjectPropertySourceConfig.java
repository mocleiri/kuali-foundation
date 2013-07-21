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
import org.kuali.common.util.maven.Maven;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.ImmutableProject;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.spring.profile.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * 
 */
@Configuration
public abstract class SmartProjectPropertySourceConfig extends BasicPropertySourceConfig {

	private static final String PROJECT_PROPERTIES_BEAN_NAME = "project.properties";

	protected abstract ImmutableProject getProject();

	@Autowired
	@Qualifier(PROJECT_PROPERTIES_BEAN_NAME)
	Properties projectProperties;

	@Override
	protected Properties getOverrides() {
		return projectProperties;
	}

	@Configuration
	@Default
	class DefaultConfig {
		@Bean(name = PROJECT_PROPERTIES_BEAN_NAME)
		public Properties projectProperties() {
			ImmutableProject project = getProject();
			String groupId = project.getGroupId();
			String artifactId = project.getArtifactId();
			Properties properties = ProjectUtils.loadProject(groupId, artifactId).getProperties();
			return new ImmutableProperties(properties);
		}
	}

	@Configuration
	@Maven
	class MavenConfig {

		@Autowired
		@Qualifier(MavenConstants.MAVEN_PROPERTIES_BEAN_NAME)
		Properties mavenProperties;

		@Bean(name = PROJECT_PROPERTIES_BEAN_NAME)
		public Properties projectProperties() {
			// Make sure the maven properties got wired in correctly
			Assert.notNull(mavenProperties, "mavenProperties are null");

			// Add in org, group, home, and enhanced version properties
			MavenUtils.augmentProjectProperties(mavenProperties);

			// Return what we've got
			return new ImmutableProperties(mavenProperties);
		}

	}

}
