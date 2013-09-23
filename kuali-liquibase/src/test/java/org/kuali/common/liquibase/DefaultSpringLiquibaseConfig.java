package org.kuali.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataSourceConfig.class)
public class DefaultSpringLiquibaseConfig implements LiquibaseConfig {

	@Override
	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase lb = new SpringLiquibase();
		return lb;
	}

}
