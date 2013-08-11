package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.EncryptionServiceConfig;
import org.kuali.common.util.enc.spring.PropertiesESCConfig;
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
@Import({ AutowiredProjectConfig.class, EncryptionServiceConfig.class, PropertiesESCConfig.class })
public class ProjectPropertiesServiceConfig implements PropertiesServiceConfig {

	@Autowired
	Project project;

	@Autowired
	EncryptionService service;

	@Override
	@Bean
	public PropertiesService propertiesService() {

		// Project properties "win" over everything except system properties
		Properties overrides = PropertyUtils.combine(project.getProperties(), PropertyUtils.getGlobalProperties());

		// Decrypt and resolve the properties after having loaded them
		ProcessorsProcessor processor = new ProcessorsProcessor(new DecryptingProcessor(service), new ResolvingProcessor());

		// Setup a service with the overrides and post processor we've configured
		return new OverridePropertiesService(overrides, processor);
	}

}
