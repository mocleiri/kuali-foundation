package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class PropertiesConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public Object whatever() {
		Properties properties = SpringUtils.getAllProperties(env);
		PropertyUtils.info(properties);
		return null;
	}

}
