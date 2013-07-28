package org.kuali.common.util.project.spring.config;

import java.util.Properties;

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectServiceConfig;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.kuali.common.util.spring.config.annotation.Runtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

@Configuration
public class AutomaticProjectConfig implements ProjectConfig {

	@Autowired
	Project project;

	@Override
	public Project project() {
		return project;
	}

	@Configuration
	@Runtime
	@Import({ ProjectServiceConfig.class })
	static class RuntimeProjectConfig {

		@Autowired
		ProjectIdentifierConfig projectIdentifierConfig;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Bean
		public Project project() {

			String groupId = projectIdentifierConfig.projectGroupId();
			String artifactId = projectIdentifierConfig.projectArtifactId();

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Use the service to convert the projectId into a Project
			return service.getProject(groupId, artifactId);
		}
	}

	@Configuration
	@Maven
	@Import({ ProjectServiceConfig.class })
	static class MavenProjectConfig {

		// Spring Maven Plugin wires this in for us
		@Autowired
		@Qualifier(MavenConstants.PROPERTIES_BEAN_NAME)
		Properties mavenProperties;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

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
}
