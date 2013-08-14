package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.service.DefaultJdbcService;
import org.kuali.common.jdbc.service.JdbcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcServiceConfig {

	@Bean
	public JdbcService jdbcService() {
		return new DefaultJdbcService();
	}

}
