package org.kuali.common.jdbc.show.spring;

import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.show.ShowConfigExecutable;
import org.kuali.common.jdbc.show.ShowDbaConfigExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DataSourceConfig.class, JdbcServiceConfig.class })
public class JdbcShowConfig {

	@Autowired
	DatabaseProcessContext context;

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Autowired
	JdbcService service;

	@Bean
	public ShowConfigExecutable showConfigExecutable() {
		return new ShowConfigExecutable(context, dataSourceConfig.dataSource(), service);
	}

	@Bean
	public ShowDbaConfigExecutable showDbaConfigExecutable() {
		return new ShowDbaConfigExecutable(context, dataSourceConfig.dbaDataSource(), service);
	}
}
