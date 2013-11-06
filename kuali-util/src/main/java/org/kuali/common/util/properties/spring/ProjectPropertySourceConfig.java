package org.kuali.common.util.properties.spring;

import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;

@Configuration
public class ProjectPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	PropertyLocationsConfig locationsConfig;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		return PropertySourceUtils.getPropertySource(service, locationsConfig.propertyLocations());
	}

}
