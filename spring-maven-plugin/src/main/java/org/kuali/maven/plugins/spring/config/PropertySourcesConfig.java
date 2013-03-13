package org.kuali.maven.plugins.spring.config;

import java.util.Properties;

import org.kuali.maven.plugins.spring.MavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class PropertySourcesConfig {

	@Bean
	public String propertySourceName() {
		return MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
	}

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public PropertiesPropertySource propertySource() {
		return new PropertiesPropertySource(propertySourceName(), mavenProperties);
	}

}
