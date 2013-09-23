package org.kuali.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultSpringLiquibaseConfig implements LiquibaseConfig {

	@Override
	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase lb = new SpringLiquibase();
		return lb;
	}

}
