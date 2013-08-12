package org.kuali.common.util.properties.spring;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
public class LocationPropertySourceConfig implements PropertySourceConfig {

	private static final String PROPERTY_SOURCE_NAME = "propertiesPropertySource";

	@Autowired
	PropertyLocationsConfig propertyLocationsConfig;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		List<Location> locations = propertyLocationsConfig.propertyLocations();
		Properties properties = service.getProperties(locations);
		return new PropertiesPropertySource(PROPERTY_SOURCE_NAME, properties);
	}

}
