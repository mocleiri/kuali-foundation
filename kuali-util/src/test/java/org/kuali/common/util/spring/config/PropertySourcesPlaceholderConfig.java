package org.kuali.common.util.spring.config;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(name = "carProperties", value = "classpath:car.properties")
public class PropertySourcesPlaceholderConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc1() {
		int order = 2;
		Resource resource = LocationUtils.getResource("classpath:car.properties");

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocations(new Resource[] { resource });
		pspc.setOrder(order);
		return pspc;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc2() {
		int order = 1;
		Resource resource = LocationUtils.getResource("classpath:chevy.properties");

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocations(new Resource[] { resource });
		pspc.setOrder(order);
		return pspc;
	}

	@Bean
	public Object whatever() {
		SpringUtils.showPropertySources(env);
		return null;
	}

}
