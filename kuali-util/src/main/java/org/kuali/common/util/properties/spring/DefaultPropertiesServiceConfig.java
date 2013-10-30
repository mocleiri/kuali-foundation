package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.property.processor.DecryptingProcessor;
import org.kuali.common.util.property.processor.OverridingProcessor;
import org.kuali.common.util.property.processor.ProcessorsProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvingProcessor;
import org.kuali.common.util.property.processor.TrimmingProcessor;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ SpringServiceConfig.class, AutowiredProjectConfig.class })
public class DefaultPropertiesServiceConfig implements PropertiesServiceConfig {

	private static final String REMOVE_SYSTEM_PROPERTY_PASSWORD_KEY = "properties.enc.password.remove";

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

		// This property contains the password for decrypting any encrypted property values
		String passwordKey = DecryptingProcessor.DEFAULT_PASSWORD_KEY;

		// Setup a processor that gets invoked on the properties *after* they have all been loaded
		PropertyProcessor processor = getPostProcessor(overrides, passwordKey);

		// Setup a service with the overrides and post processor we've configured
		PropertiesService service = new DefaultPropertiesService(overrides, processor);

		// Now that the service is setup, we can remove the password as a system property (if it has been set there)
		if (env.getBoolean(REMOVE_SYSTEM_PROPERTY_PASSWORD_KEY, true)) {
			PropertyUtils.removeSystemProperty(passwordKey);
		}

		// Return the configured service
		return service;
	}

	protected PropertyProcessor getPostProcessor(Properties overrides, String passwordKey) {
		PropertyProcessor override = new OverridingProcessor(overrides);
		PropertyProcessor decrypt = new DecryptingProcessor(passwordKey);
		PropertyProcessor resolve = new ResolvingProcessor();
		PropertyProcessor trim = new TrimmingProcessor(ImmutableList.of(passwordKey), ImmutableList.<String> of());
		return new ProcessorsProcessor(override, decrypt, resolve, trim);
	}

}
