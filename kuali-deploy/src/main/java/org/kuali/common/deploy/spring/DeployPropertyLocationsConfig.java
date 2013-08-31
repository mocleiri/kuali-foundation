package org.kuali.common.deploy.spring;

import java.util.List;

import org.kuali.common.deploy.project.DeployProjectConstants;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ AutowiredProjectConfig.class, ProjectServiceConfig.class })
public class DeployPropertyLocationsConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	ProjectService service;

	@Autowired
	Project project;

	@Bean
	public List<Location> deployPropertyLocations() {
		Location kualiDefaults = kualiDeployDefaults();
		Location groupDefaults = kualiDeployGroupDefaults();
		Location appDefaults = kualiDeployApplicationDefaults();
		Location envDefaults = kualiDeployEnvDefaults();
		return ImmutableList.of(kualiDefaults, groupDefaults, appDefaults, envDefaults);
	}

	@Bean
	public String kualiDeployEncoding() {
		Project project = service.getProject(DeployProjectConstants.ID);
		return ProjectUtils.getEncoding(project);
	}

	@Bean
	public Location kualiDeployEnvDefaults() {
		String environmentNumber = env.getString("deploy.env");
		String path = kualiDeployGroupPrefix() + "/env" + environmentNumber + ".properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	@Bean
	public Location kualiDeployApplicationDefaults() {
		String path = kualiDeployGroupPrefix() + "/" + project.getArtifactId() + ".properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	@Bean
	public String kualiDeployGroupPrefix() {
		return ProjectUtils.getClasspathPrefix(project.getGroupId());
	}

	@Bean
	public Location kualiDeployGroupDefaults() {
		String path = kualiDeployGroupPrefix() + "/deploy.properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	@Bean
	public Location kualiDeployDefaults() {
		String prefix = ProjectUtils.getClasspathPrefix(DeployProjectConstants.ID.getGroupId());
		String path = prefix + "/deploy/deploy.properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

}
