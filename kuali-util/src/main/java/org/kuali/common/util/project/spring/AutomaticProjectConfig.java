package org.kuali.common.util.project.spring;

import java.util.Properties;

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.kuali.common.util.spring.config.annotation.NotMaven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

/**
 * This config class wires a <code>Project</code> object into the Spring context. If the <code>maven</code> profile is active it assumes "something else" (most likely the
 * spring-maven-plugin) has wired a <code>java.util.Properties</code> bean under the name <code>mavenProperties</code> into the Spring context. It uses those properties to obtain a
 * <code>Project</code> object. If the <code>maven</code> profile is not active, it assumes "something else" has wired in a <code>ProjectIdentifierConfig</code> and uses that to
 * obtain a <code>Project</code> object.
 */
@Configuration
public class AutomaticProjectConfig implements ProjectConfig {

	// There can be only two results here:
	// 1 - A project object is successfully constructed and wired into the Spring context
	// 2 - An exception is thrown
	// The pair of static classes below, are setup to activate with maven and !maven, so one (and only one) of them will always load
	@Autowired
	Project project;

	@Override
	@Bean
	public Project project() {
		return project;
	}

	// This config class only loads if the Spring profile "maven" is NOT active
	@Configuration
	@NotMaven
	@Import({ ProjectServiceConfig.class })
	static class NotMavenProjectConfig {

		// Something else needs to have wired this in
		@Autowired
		ProjectIdentifierConfig projectIdentifierConfig;

		@Autowired
		ProjectServiceConfig projectServiceConfig;

		@Bean
		public Project project() {

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			String groupId = projectIdentifierConfig.projectGroupId();
			String artifactId = projectIdentifierConfig.projectArtifactId();

			// Use the service to convert the projectId into a Project
			return service.getProject(groupId, artifactId);
		}
	}

	// This config class only loads if the Spring profile "maven" IS active
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

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Enhance the default set of Maven properties
			MavenUtils.augmentProjectProperties(service, mavenProperties);

			// Use the service to convert the properties into a Project
			return service.getProject(mavenProperties);
		}
	}
}
