package org.kuali.common.liquibase.spring;

import org.kuali.common.liquibase.DefaultLiquibaseService;
import org.kuali.common.liquibase.LiquibaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfig {

	@Bean
	public LiquibaseService liquibaseService() {
		return new DefaultLiquibaseService();
	}

}
