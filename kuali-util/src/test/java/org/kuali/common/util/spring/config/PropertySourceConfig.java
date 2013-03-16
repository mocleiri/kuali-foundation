package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

@Configuration
public class PropertySourceConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public PropertiesPropertySource pps() {
		String name = "carProperties";
		Properties properties = new Properties();
		properties.setProperty("model", "ford");
		PropertiesPropertySource pps = new PropertiesPropertySource(name, properties);
		MutablePropertySources mps = env.getPropertySources();
		mps.addLast(pps);
		System.out.println(env.getRequiredProperty("model"));
		return pps;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${model}")
	String model;

	@Bean
	Object whatever() {
		System.out.println(model);
		return null;
	}

}
