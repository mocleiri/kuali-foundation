package org.kuali.common.deploy.spring;

import java.util.List;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ BaseLocationsConfig.class })
public class DeployLocationsConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	BaseLocationsConfig config;

	@Autowired
	Project project;

	@Bean
	public List<Location> deployPropertyLocations() {
		Location kualiDefaults = config.kualiDeployDefaults();
		Location groupDefaults = config.kualiDeployGroupDefaults();
		Location appDefaults = config.kualiDeployApplicationDefaults();
		Location envDefaults = kualiDeployEnvDefaults();
		return ImmutableList.of(kualiDefaults, groupDefaults, appDefaults, envDefaults);
	}

	@Bean
	public Location kualiDeployEnvDefaults() {
		String environmentNumber = env.getString("deploy.env");
		String path = config.kualiDeployGroupPrefix() + "/env" + environmentNumber + ".properties";
		return new Location(path, config.kualiDeployEncoding(), true);
	}
}
