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
 * For Spring process launched by Maven via the spring-maven-plugin (which activates the Spring profile "maven") the project wiring is completed entirely in memory using the
 * <code>java.util.Properties</code> object from the Maven runtime.
 * </p>
 * 
 * <p>
 * For Spring process launched using any other method, the project wiring is completed by loading the <code>project.properties</code> file corresponding to the
 * <code>groupId:artifactId</code> for the current project. The properties file for the <code>kuali-util</code> project (for example) is located at:
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
	// The pair of static classes below, are setup to activate via the Spring profiles "maven" and "!maven"
	// This makes it so that one (and only one) of them will always load no matter what

	// This config class loads if the Spring profile "maven" is NOT active
	@Configuration
	@NotMaven
	@Import({ ProjectServiceConfig.class })
	static class NotMavenProjectConfig implements ProjectConfig {

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

			String groupId = projectIdentifierConfig.projectGroupId();
			String artifactId = projectIdentifierConfig.projectArtifactId();

			// Use the service to convert the projectId into a Project
			return service.getProject(groupId, artifactId);
		}
	}

	// This config class loads if the Spring profile "maven" IS active
	@Configuration
	@Maven
	@Import({ ProjectServiceConfig.class })
	static class MavenProjectConfig implements ProjectConfig {

		// spring-maven-plugin wires this in for us
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

			// Get a reference to the project service
			ProjectService service = projectServiceConfig.projectService();

			// Enhance the default set of Maven properties
			MavenUtils.augmentProjectProperties(service, mavenProperties);

			// Use the service to convert the properties into a Project
			return service.getProject(mavenProperties);
		}
	}
}
