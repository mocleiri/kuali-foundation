package org.kuali.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataSourceConfig.class)
public class DefaultSpringLiquibaseConfig implements LiquibaseConfig {

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Override
	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase lb = new SpringLiquibase();
		lb.setDataSource(dataSourceConfig.dataSource());
		lb.setChangeLog("org/kuali/rice/rice-liquibase/initial-db/change-log.xml");
		lb.setContexts("master");
		lb.setDropFirst(true);
		return lb;
	}

}
