package org.kuali.common.util.properties.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.OverridePropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.processor.DecryptingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
import org.kuali.common.util.property.processor.TrimProcessor;
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

		// Project properties "win" over everything except system properties
		Properties overrides = PropertyUtils.combine(project.getProperties(), PropertyUtils.getGlobalProperties());

		// Decrypt and resolve the properties after having loaded them
		PropertyProcessor processor = getPostProcessor();

		// Setup a service with the overrides and post processor we've configured
		return new OverridePropertiesService(overrides, processor);
	}

	protected PropertyProcessor getPostProcessor() {
		DecryptingProcessor decryptor = new DecryptingProcessor();
		ResolvingProcessor resolver = new ResolvingProcessor();
		TrimProcessor trimmer = new TrimProcessor(decryptor.getPasswordKey());
		List<PropertyProcessor> processors = Arrays.asList(decryptor, resolver, trimmer);
		return new ProcessorsProcessor(processors);
	}

}
