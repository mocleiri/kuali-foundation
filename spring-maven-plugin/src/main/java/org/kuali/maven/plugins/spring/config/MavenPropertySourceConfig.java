package org.kuali.maven.plugins.spring.config;

import java.util.Properties;

import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
public class MavenPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		String name = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		Properties source = mavenProperties;
		return new PropertiesPropertySource(name, source);
	}

}
