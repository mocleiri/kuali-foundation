package org.kuali.common.util.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SpringServiceConfig {

	@Autowired
	Environment env;

	@Bean
	public SpringService springService() {
		return new DefaultSpringService();
	}

}
