package org.kuali.common.util.spring.config;

import org.kuali.common.util.LocationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

@Configuration
public class PropertySourcePlaceholderConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc1() {
		int order = 1;
		Resource resource = LocationUtils.getResource("claspath:car.properties");

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocation(resource);
		pspc.setOrder(order);
		return pspc;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc2() {
		int order = 2;
		Resource resource = LocationUtils.getResource("claspath:chevy.properties");

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocation(resource);
		pspc.setOrder(order);
		return pspc;
	}

}
