package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.OverridePropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class ProjectPropertiesServiceConfig {

	@Autowired
	Project project;

	@Bean
	public PropertiesService projectPropertiesService() {

		// Get system + environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Setup a service where project properties "win" over everything except system properties
		return new OverridePropertiesService(project.getProperties(), global);
	}

}
