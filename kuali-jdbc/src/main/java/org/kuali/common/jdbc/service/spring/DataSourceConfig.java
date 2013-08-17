package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
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
	public DataSource dataSourceDba() {
		ConnectionContext dba = context.getConnections().getDba();
		return getDataSource(dba, jdbcDriver());
	}

	@Bean
	public Class<? extends Driver> jdbcDriver() {
		return context.getConnections().getDriver();
	}

	protected DataSource getDataSource(ConnectionContext context, Class<? extends Driver> driver) {
		Credentials creds = context.getCredentials();
		String username = toNull(creds.getUsername(), Credentials.NO_USERNAME);
		String password = toNull(creds.getPassword(), Credentials.NO_PASSWORD);
		DriverManagerDataSource dmds = new DriverManagerDataSource(context.getUrl(), username, password);
		dmds.setDriverClassName(driver.getName());
		return dmds;
	}

	protected String toNull(String token, String nullToken) {
		return StringUtils.equals(token, nullToken) ? null : token;
	}
}
