package org.kuali.common.util.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class PropertySourceServiceConfig {

	@Autowired
	SpringServiceConfig springServiceConfig;

	@Bean
	public PropertySourceService propertySourceService() {
		DefaultPropertySourceService service = new DefaultPropertySourceService();
		service.setSpringService(springServiceConfig.springService());
		return service;
	}

}
