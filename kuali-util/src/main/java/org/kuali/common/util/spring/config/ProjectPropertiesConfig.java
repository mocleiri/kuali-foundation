package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectPropertiesConfig {

	@Autowired
	@Qualifier("mavenProperties")
	Properties mavenProperties;

	@Bean
	public Properties augmentedProjectProperties() {
		return mavenProperties;
	}

}
