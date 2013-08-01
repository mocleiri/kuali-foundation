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

		// Setup a properties object where system/environment properties "win" over project properties
		Properties overrides = PropertyUtils.getGlobalProperties(project.getProperties());

		// Setup a service where system + project properties "win" over properties loaded from all other locations
		return new OverridePropertiesService(overrides);
	}

}
