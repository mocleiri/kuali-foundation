package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.util.nullify.NullUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Import({ DatabaseProcessContextConfig.class })
public class DataSourceConfig {

	@Autowired
	DatabaseProcessContext context;

	@Bean
	public DataSource dataSource() {
		ConnectionContext normal = context.getConnections().getNormal();
		return getDataSource(normal, jdbcDriver());
	}

	@Bean
	public DataSource dbaDtaSource() {
		ConnectionContext dba = context.getConnections().getDba();
		return getDataSource(dba, jdbcDriver());
	}

	@Bean
	public Class<? extends Driver> jdbcDriver() {
		return context.getConnections().getDriver();
	}

	protected DataSource getDataSource(ConnectionContext context, Class<? extends Driver> driver) {
		Credentials creds = context.getCredentials();
		String username = NullUtils.toNull(creds.getUsername());
		String password = NullUtils.toNull(creds.getPassword());
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(driver.getName());
		dmds.setUrl(context.getUrl());
		dmds.setUsername(username);
		dmds.setPassword(password);
		return dmds;
	}
}
