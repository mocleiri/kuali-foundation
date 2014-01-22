package org.kuali.common.util.system.spring;

import org.kuali.common.util.system.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DefaultSystemConfig implements SystemConfig {

	@Autowired
	Environment env;

	@Override
	@Bean
	public SystemProperties systemProperties() {
		return null;
	}

}
