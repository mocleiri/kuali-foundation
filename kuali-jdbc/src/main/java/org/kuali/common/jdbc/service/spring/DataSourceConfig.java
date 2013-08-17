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
		String username = StringUtils.equals(creds.getUsername(), Credentials.NO_USERNAME) ? null : creds.getUsername();
		String password = StringUtils.equals(creds.getPassword(), Credentials.NO_PASSWORD) ? null : creds.getPassword();
		DriverManagerDataSource dmds = new DriverManagerDataSource(context.getUrl(), username, password);
		dmds.setDriverClassName(driver.getName());
		return dmds;
	}
}
