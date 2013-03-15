package org.kuali.common.util.spring.config;

import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Augment the default set of properties that ship with Maven and register them as a Spring <code>PropertySource</code>.
 * 
 * spring-maven-plugin auto-registers any beans that implement <code>PropertySource</code> as a top level property source
 */
@Configuration
public class ProjectPropertySourceConfig {

	/**
	 * spring-maven-plugin auto-wires Maven properties by default
	 */
	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public PropertiesPropertySource projectPropertySource() {

		// Setup some processors
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		// Add some organization, group, and path properties
		processors.add(new ProjectProcessor());

		// Tokenize the version number and add properties for each token (major/minor/incremental)
		// Also add a boolean property indicating if this is a SNAPSHOT build
		processors.add(new VersionProcessor(Arrays.asList("project.version"), true));

		// Process default Maven properties to add in our custom properties
		PropertyUtils.process(mavenProperties, processors);

		// Return the augmented set of Maven properties as a Spring PropertySource
		String name = Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		return new PropertiesPropertySource(name, mavenProperties);
	}
}
