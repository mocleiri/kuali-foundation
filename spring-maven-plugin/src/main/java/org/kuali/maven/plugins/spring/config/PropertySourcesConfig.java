package org.kuali.maven.plugins.spring.config;

import org.kuali.maven.plugins.spring.LoadMojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertySourcesConfig {

	@Bean
	@Autowired(required = true)
	public LoadMojo mojo() {
		return null;
	}

}
