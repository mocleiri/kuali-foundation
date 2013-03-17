package org.kuali.common.util.spring.car;

import java.util.Arrays;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class CarConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public Object whatever() {
		SpringUtils.showPropertySources(env);
		Properties all = SpringUtils.getAllEnumerableProperties(env);
		PropertyUtils.trim(all, Arrays.asList("car.*"), null);
		PropertyUtils.info(all);
		return null;
	}
}
