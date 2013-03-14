package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.kuali.common.util.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectPropertiesConfig {

	@Autowired
	@Qualifier("mavenProperties")
	Properties mavenProperties;

	@Bean
	public Properties augmentedProjectProperties() {
		String groupId = mavenProperties.getProperty("project.groupId");
		String groupIdPath = Str.getPath(groupId);
		mavenProperties.setProperty("project.groupId.path", groupIdPath);
		return mavenProperties;
	}

}
