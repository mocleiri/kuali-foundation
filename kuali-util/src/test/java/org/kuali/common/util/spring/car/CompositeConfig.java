package org.kuali.common.util.spring.car;

import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ CarConfig.class, MakeConfig.class })
public class CompositeConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public Object whatever() {
		SpringUtils.showPropertySources(env);
		return null;
	}
}
