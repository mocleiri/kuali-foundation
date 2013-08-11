package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.OverridePropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.processor.DecryptingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class ProjectPropertiesServiceConfig implements PropertiesServiceConfig {

	@Autowired
	Project project;

	@Override
	@Bean
	public PropertiesService propertiesService() {

		// Decrypt and resolve the properties after having loaded them
		ProcessorsProcessor postProcessor = new ProcessorsProcessor(new DecryptingProcessor(), new ResolvingProcessor());

		// Setup a properties object where project properties "win" over everything except system properties
		Properties overrides = PropertyUtils.combine(project.getProperties(), PropertyUtils.getGlobalProperties());

		// Setup a service with the overrides and postProcessor we've configured
		return new OverridePropertiesService(overrides, postProcessor);
	}

}
