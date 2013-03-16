package org.kuali.common.util.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertySourcesPlaceholderConfig {

	@Bean
	public Object b() {
		System.out.println("b");
		return null;
	}

	@Bean
	public Object d() {
		System.out.println("d");
		return null;
	}

	@Bean
	public Object c() {
		System.out.println("c");
		return null;
	}

}