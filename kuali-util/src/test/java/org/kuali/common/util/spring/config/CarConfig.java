package org.kuali.common.util.spring.config;

import java.util.Arrays;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@PropertySource(name = "carProperties", value = { "classpath:car.properties", "${car.make}.properties" })
public class CarConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public Object whatever() {
		Properties car = SpringUtils.getAllProperties(env);
		PropertyUtils.trim(car, Arrays.asList("car.*"), null);
		PropertyUtils.info(car);
		return null;
	}

}
