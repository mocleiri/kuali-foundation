package org.kuali.maven.plugins.spring.config;

import static org.kuali.maven.plugins.spring.MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;

import java.util.Properties;

import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
public class MavenPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	@Qualifier(DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		String name = DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		Properties source = mavenProperties;
		return new PropertiesPropertySource(name, source);
	}

}
