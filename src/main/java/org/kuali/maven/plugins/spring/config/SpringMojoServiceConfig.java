package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.kuali.maven.plugins.spring.DefaultSpringMojoService;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class SpringMojoServiceConfig {

	@Autowired
	SpringServiceConfig springServiceConfig;

	@Bean
	public SpringMojoService springMojoService() {
		DefaultSpringMojoService service = new DefaultSpringMojoService();
		service.setPropertySourceService(springServiceConfig.propertySourceService());
		return service;
	}
}
