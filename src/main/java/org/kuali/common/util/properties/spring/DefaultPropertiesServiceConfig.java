package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionContext;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.property.processor.ContextDecryptingProcessor;
import org.kuali.common.util.property.processor.OverridingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
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

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public PropertiesService propertiesService() {

		// Get a reference to system + environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Setup a properties object where system properties "win" over project properties
		Properties overrides = new ImmutableProperties(PropertyUtils.combine(project.getProperties(), global));

		// Setup an encryption context from the overrides properties
		EncryptionContext context = EncUtils.getEncryptionContext(overrides);

		// Setup a processor that gets invoked on the properties *after* they have all been loaded
		PropertyProcessor processor = getPostProcessor(overrides, context);

		// Setup a service with the overrides and post processor we've configured
		PropertiesService service = new DefaultPropertiesService(overrides, processor);

		// Now that the service is setup, we can remove the password as a system property (if it has been set there)
		if (context.getPasswordKey().isPresent() && context.isRemovePasswordSystemProperty()) {
			PropertyUtils.removeSystemProperty(context.getPasswordKey().get());
		}

		// Return the configured service
		return service;
	}

	protected PropertyProcessor getPostProcessor(Properties overrides, EncryptionContext context) {
		PropertyProcessor override = new OverridingProcessor(overrides);
		PropertyProcessor decrypt = new ContextDecryptingProcessor(context);
		PropertyProcessor resolve = new ResolvingProcessor();
		return new ProcessorsProcessor(override, decrypt, resolve);
	}

}
