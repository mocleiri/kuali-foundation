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

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public PropertiesPropertySource propertySource() {
		String name = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		Properties source = mavenProperties;
		return new PropertiesPropertySource(name, source);
	}

}
