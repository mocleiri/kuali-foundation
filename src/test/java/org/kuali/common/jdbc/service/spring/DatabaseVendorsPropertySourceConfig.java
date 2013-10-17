package org.kuali.common.jdbc.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.project.spring.JdbcProjectConfig;
import org.kuali.common.jdbc.project.spring.JdbcPropertyLocationsConfig;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.properties.spring.DefaultPropertiesServiceConfig;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcProjectConfig.class, JdbcPropertyLocationsConfig.class, DefaultPropertiesServiceConfig.class })
public class DatabaseVendorsPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	JdbcPropertyLocationsConfig jdbc;

	@Autowired
	Project project;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		List<Location> locations = new ArrayList<Location>(jdbc.jdbcPropertyLocations());
		locations.add(new Location("classpath:jc.properties", ProjectUtils.getEncoding(project)));
		return PropertySourceUtils.getPropertySource(service, locations);
	}
}
