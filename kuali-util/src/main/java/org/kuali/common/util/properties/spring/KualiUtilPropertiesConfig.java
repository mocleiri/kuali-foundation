package org.kuali.common.util.properties.spring;

import java.util.Arrays;

import org.kuali.common.util.properties.PropertiesContext;
import org.kuali.common.util.properties.PropertiesLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KualiUtilPropertiesConfig {

	@Bean
	public PropertiesContext propertiesContext() {
		PropertiesContext context = new PropertiesContext();
		return context;
	}

	protected PropertiesContext getScm() {
		PropertiesContext context = new PropertiesContext();
		context.setId("org.kuali.common:kuali-util:scm");
		context.setLocations(Arrays.asList(new PropertiesLocation()));
		return context;
	}

}
