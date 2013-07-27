package org.kuali.common.util.spring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringServiceConfig {

	@Bean
	public SpringService springService() {
		return new DefaultSpringService();
	}

}
