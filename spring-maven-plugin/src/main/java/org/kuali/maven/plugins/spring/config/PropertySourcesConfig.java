package org.kuali.maven.plugins.spring.config;

import org.kuali.maven.plugins.spring.LoadMojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class PropertySourcesConfig {

	@Bean
	public PropertiesPropertySource propertySource() {
		PropertiesPropertySource pps = new PropertiesPropertySource(LoadMojo.MAVEN_PROPERTIES, null);
		return pps;
	}

}
