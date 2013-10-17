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
package org.kuali.common.util.project.spring;

import java.util.Properties;

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.maven.spring.AutowiredMavenProperties;
import org.kuali.common.util.maven.spring.NoAutowiredMavenProperties;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

/**
 * <p>
 * This class automatically wires a <code>Project</code> object into the Spring context:
 * 
 * <pre>
 * &#064;Autowired
 * Project project;
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * For Spring process launched by Maven via the spring-maven-plugin the project wiring is completed entirely in memory using the <code>java.util.Properties</code> object from the
 * Maven runtime.
 * </p>
 * 
 * <p>
 * For Spring process launched using any other method, the project wiring is completed by loading the <code>project.properties</code> file corresponding to the
 * <code>[groupId:artifactId]</code> for the current project. The properties file for the <code>kuali-util</code> project (for example) is located at:
 * 
 * <pre>
 * classpath:META-INF/org/kuali/common/kuali-util/project.properties
 * </pre>
 * 
 * The <code>project.properties</code> file for every Kuali project is automatically created by Maven early in the default build lifecycle, (the <code>generate-resources</code>
 * phase) and is thus available to any build process that comes after that. For example, the <code>test</code> phase.
 * 
 * </p>
 */
@Configuration
public class AutowiredProjectConfig {

	// There can be only two results here:
	// 1 - A project object is successfully constructed and wired into the Spring context
	// 2 - An exception is thrown
	// The pair of static classes below, are setup to activate via the Spring profiles "autowiredMavenProperties" and "!autowiredMavenProperties"
	// This makes it so that one (and only one) of them will always load no matter what

	// This config class loads if the Spring profile "autowiredMavenProperties" is NOT active (which will usually be the case)
	@Configuration
	@NoAutowiredMavenProperties
	@Import({ ProjectServiceConfig.class })
	static class NoAutowiredMavenPropertiesProjectConfig implements ProjectConfig {

		// Something else needs to have wired this in
		@Autowired
		ProjectIdentifierConfig projectIdentifierConfig;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Override
		@Bean
		public Project project() {

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Get a reference to a project identifier (groupId + artifactId)
			ProjectIdentifier identifier = projectIdentifierConfig.projectIdentifier();

			// Use the service to load the correct project.properties file and convert to a Project object
			return service.getProject(identifier.getGroupId(), identifier.getArtifactId());
		}
	}

	// This config class only loads if the Spring profile "autowiredMavenProperties" is active
	// spring-maven-plugin activates this profile by default when Spring is launched by Maven during a build
	@Configuration
	@AutowiredMavenProperties
	static class AutowiredMavenPropertiesProjectConfig implements ProjectConfig {

		// spring-maven-plugin wires this in for us
		@Autowired
		@Qualifier(MavenConstants.PROPERTIES_BEAN_NAME)
		Properties mavenProperties;

		@Override
		@Bean
		public Project project() {

			// Make sure the maven properties got wired in correctly
			Assert.notNull(mavenProperties, "mavenProperties are null");

			// Enhance the default set of Maven properties
			MavenUtils.augmentProjectProperties(mavenProperties);

			// Convert the augmented properties into a Project object
			return ProjectUtils.getProject(mavenProperties);
		}

		@Bean
		public Build build() {
			return ProjectUtils.getBuild(project());
		}

	}
}
