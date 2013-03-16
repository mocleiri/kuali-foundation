package org.kuali.common.util.spring.config;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import(CarConfig.class)
@PropertySource(name = "makeProperties", value = "classpath:${make}.properties")
public class MakeConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public Object whatever() {
		Properties p = SpringUtils.getAllEnumerableProperties(env);
		PropertyUtils.info(p);
		return null;
	}

}
