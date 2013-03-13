package org.kuali.maven.plugins.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class PropertySourcesConfig {

	@Bean
	public PropertiesPropertySource propertySource() {
		PropertiesPropertySource pps = new PropertiesPropertySource("", null);
		return pps;
	}

}
