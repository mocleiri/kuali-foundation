package org.kuali.common.util.properties.spring;

import java.util.Arrays;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.processor.DecryptingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
import org.kuali.common.util.property.processor.TrimmingProcessor;
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

		// This property contains the password for decrypting any encrypted values
		String passwordKey = DecryptingProcessor.DEFAULT_PASSWORD_KEY;

		// Decrypt and resolve the properties after having loaded them
		PropertyProcessor processor = getPostProcessor(passwordKey);

		// Setup a service with the overrides and post processor we've configured
		return new DefaultPropertiesService(overrides, processor, passwordKey);
	}

	protected PropertyProcessor getPostProcessor(String passwordKey) {
		PropertyProcessor decryptor = new DecryptingProcessor(passwordKey);
		PropertyProcessor resolver = new ResolvingProcessor();
		PropertyProcessor trimmer = new TrimmingProcessor(passwordKey);
		return new ProcessorsProcessor(Arrays.asList(decryptor, resolver, trimmer));
	}

}
