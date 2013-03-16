package org.kuali.common.util.spring.car;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class CarPropertySourcesConfig {

	@Bean
	public Properties base() {
		return PropertyUtils.load("classpath:car.properties");
	}

	@Bean
	public Properties make() {
		Properties base = base();
		String make = base.getProperty("car.make");
		return PropertyUtils.load("classpath:" + make + ".properties");
	}

	@Bean
	public PropertiesPropertySource pps() {
		String name = "carProperties";
		Properties source = PropertyUtils.combine(base(), make());
		return new PropertiesPropertySource(name, source);
	}
}
