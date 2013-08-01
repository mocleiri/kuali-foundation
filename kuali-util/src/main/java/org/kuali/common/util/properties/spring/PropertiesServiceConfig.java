package org.kuali.common.util.properties.spring;

import org.kuali.common.util.properties.DefaultPropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesServiceConfig {

	@Bean
	public PropertiesService propertiesService() {
		return new DefaultPropertiesService();
	}

}
