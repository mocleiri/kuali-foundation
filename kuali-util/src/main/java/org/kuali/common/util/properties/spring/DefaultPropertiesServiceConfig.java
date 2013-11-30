package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncContext;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.property.processor.EncContextDecryptingProcessor;
import org.kuali.common.util.property.processor.OverridingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, AutowiredProjectConfig.class })
public class DefaultPropertiesServiceConfig implements PropertiesServiceConfig {

	@Autowired
	Project project;

	@Override
	@Bean
	public PropertiesService propertiesService() {

		// Get a reference to system + environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Setup a properties object where system properties "win" over project properties
		Properties overrides = new ImmutableProperties(PropertyUtils.combine(project.getProperties(), global));

		// Setup an encryption context from the overrides properties
		EnvironmentService basic = new BasicEnvironmentService(overrides);
		EncContext context = new EncContext.Builder().env(basic).build();

		// Setup a processor that gets invoked on the properties *after* they have all been loaded
		PropertyProcessor processor = getPostProcessor(overrides, context);

		// Setup a service with the overrides and post processor we've configured
		PropertiesService service = new DefaultPropertiesService(overrides, processor);

		// Now that the service is setup, we can remove any system properties that may contain the encryption password
		if (context.isRemovePasswordSystemProperty()) {
			for (String key : context.getPasswordKeys()) {
				PropertyUtils.removeSystemProperty(key);
			}
		}

		// Return the configured service
		return service;
	}

	protected PropertyProcessor getPostProcessor(Properties overrides, EncContext context) {
		PropertyProcessor override = new OverridingProcessor(overrides);
		PropertyProcessor decrypt = new EncContextDecryptingProcessor(context);
		PropertyProcessor resolve = new ResolvingProcessor();
		return new ProcessorsProcessor(override, decrypt, resolve);
	}

}
