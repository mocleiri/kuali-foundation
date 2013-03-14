package org.kuali.common.util.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.processor.ProjectProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
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
	public List<PropertyProcessor> processors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		processors.add(new ProjectProcessor());
		processors.add(new VersionProcessor("project.version"));
		return processors;
	}

	@Bean
	public Properties augmentedProjectProperties() {
		PropertyUtils.process(mavenProperties, processors());
		return mavenProperties;
	}

	@Bean
	public PropertiesPropertySource propertySource() {
		String name = Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		return new PropertiesPropertySource(name, augmentedProjectProperties());
	}
}
