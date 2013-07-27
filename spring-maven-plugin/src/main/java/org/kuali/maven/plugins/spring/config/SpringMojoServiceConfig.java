package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.spring.service.PropertySourceServiceConfig;
import org.kuali.maven.plugins.spring.DefaultSpringMojoService;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertySourceServiceConfig.class })
public class SpringMojoServiceConfig {

	@Autowired
	PropertySourceServiceConfig propertySourceServiceConfig;

	@Bean
	public SpringMojoService springMojoService() {
		DefaultSpringMojoService service = new DefaultSpringMojoService();
		service.setPropertySourceService(propertySourceServiceConfig.propertySourceService());
		return service;
	}
}
