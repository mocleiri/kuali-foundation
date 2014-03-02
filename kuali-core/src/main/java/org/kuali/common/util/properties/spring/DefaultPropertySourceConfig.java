package org.kuali.common.util.properties.spring;

import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;

@Configuration
public class DefaultPropertySourceConfig implements PropertySourceConfig {

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		return PropertySourceUtils.getDefaultPropertySource();
	}

}
