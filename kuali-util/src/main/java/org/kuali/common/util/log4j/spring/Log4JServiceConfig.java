package org.kuali.common.util.log4j.spring;

import org.kuali.common.util.log4j.DefaultLog4JService;
import org.kuali.common.util.log4j.Log4JService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JServiceConfig {

	@Bean
	public Log4JService log4jService() {
		return new DefaultLog4JService();
	}

}
