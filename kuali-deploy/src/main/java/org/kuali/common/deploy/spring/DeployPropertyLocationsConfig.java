package org.kuali.common.deploy.spring;

import java.util.List;

import org.kuali.common.deploy.project.DeployProjectConstants;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ ProjectServiceConfig.class })
public class DeployPropertyLocationsConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	ProjectService service;

	@Bean
	public List<Location> deployPropertyLocations() {
		Location kualiDefaults = getDeployDefaults();
		Location groupDefaults = getGroupDefaults();
		Location appDefaults = getApplicationDefaults();
		Location envDefaults = getEnvDefaults();
		return ImmutableList.of(kualiDefaults, groupDefaults, appDefaults, envDefaults);
	}

	@Bean
	public String kualiDeployEncoding() {
		Project project = service.getProject(DeployProjectConstants.ID);
		return ProjectUtils.getEncoding(project);
	}

	protected Location getEnvDefaults() {
		String environmentNumber = env.getString("deploy.env");
		String path = getGroupPrefix() + "/env" + environmentNumber + ".properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	protected Location getApplicationDefaults() {
		String artifactId = env.getString("project.artifactId");
		String path = getGroupPrefix() + "/" + artifactId + ".properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	protected String getGroupPrefix() {
		String groupId = env.getString("project.groupId");
		return ProjectUtils.getClasspathPrefix(groupId);
	}

	protected Location getGroupDefaults() {
		String path = getGroupPrefix() + "/deploy.properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

	protected Location getDeployDefaults() {
		String prefix = ProjectUtils.getClasspathPrefix(DeployProjectConstants.ID.getGroupId());
		String path = prefix + "/deploy/deploy.properties";
		return new Location(path, kualiDeployEncoding(), true);
	}

}
