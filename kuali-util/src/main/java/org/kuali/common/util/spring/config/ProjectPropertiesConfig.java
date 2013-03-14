package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class ProjectPropertiesConfig {

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public Properties augmentedProjectProperties() {
		String groupId = mavenProperties.getProperty("project.groupId");
		String groupIdPath = Str.getPath(groupId);
		mavenProperties.setProperty("project.groupId.path", groupIdPath);
		return mavenProperties;
	}

	@Bean
	public PropertiesPropertySource propertySource() {
		String name = Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		return new PropertiesPropertySource(name, mavenProperties);
	}
}
