package org.kuali.common.util.properties.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncContext;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.property.processor.JasyptDecryptingProcessor;
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

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public PropertiesService propertiesService() {
		Properties overrides = getOverrides(project);
		PropertyProcessor processor = getPostProcessor(overrides);
		return new DefaultPropertiesService(overrides, processor);
	}

	private Properties getOverrides(Project project) {
		// Get a reference to system + environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Setup a properties object where system properties "win" over project properties
		return ImmutableProperties.of(PropertyUtils.combine(project.getProperties(), global));
	}

	private PropertyProcessor getPostProcessor(Properties overrides) {
		EnvironmentService env = new BasicEnvironmentService(overrides);
		EncContext context = EncContext.builder(env).removeSystemProperties(true).build();
		PropertyProcessor override = new OverridingProcessor(overrides);
		PropertyProcessor decrypt = new JasyptDecryptingProcessor(context.getTextEncryptor());
		PropertyProcessor resolver = new ResolvingProcessor();
		return new ProcessorsProcessor(override, decrypt, resolver);
	}

}
