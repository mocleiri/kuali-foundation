package org.kuali.maven.plugins.spring.config;

import java.util.Properties;

import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class PropertySourcesConfig {

	@Bean
	public String propertySourceName() {
		return AbstractSpringMojo.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
	}

	@Autowired
	@Qualifier(AbstractSpringMojo.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties propertySourceProperties;

	@Bean
	public PropertiesPropertySource propertySource() {
		System.out.println("propertySourceProperties.size()=" + propertySourceProperties.size());
		return new PropertiesPropertySource(propertySourceName(), propertySourceProperties);
	}

}
