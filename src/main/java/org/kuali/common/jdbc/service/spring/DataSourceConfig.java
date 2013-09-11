package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.property.ImmutableProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@Import({ DatabaseProcessContextConfig.class })
public class DataSourceConfig {

	private static final String USERNAME_KEY = "user";
	private static final String PASSWORD_KEY = "password";

	@Autowired
	DatabaseProcessContext context;

	@Bean
	public DataSource dataSource() {
		ConnectionContext normal = context.getConnections().getNormal();
		return getDataSource(normal, jdbcDriver());
	}

	@Bean
	public DataSource dbaDataSource() {
		ConnectionContext dba = context.getConnections().getDba();
		return getDataSource(dba, jdbcDriver());
	}

	@Bean
	public Class<? extends Driver> jdbcDriverClass() {
		return context.getConnections().getDriver();
	}

	@Bean
	public Driver jdbcDriver() {
		return BeanUtils.instantiateClass(jdbcDriverClass());
	}

	protected DataSource getDataSource(ConnectionContext context, Driver driver) {
		Properties properties = getProperties(context);
		return new SimpleDriverDataSource(driver, context.getUrl(), properties);
	}

	protected Properties getProperties(ConnectionContext context) {
		Credentials creds = context.getCredentials();
		String username = toNull(creds.getUsername(), Credentials.NO_USERNAME);
		String password = toNull(creds.getPassword(), Credentials.NO_PASSWORD);
		Properties properties = new Properties(context.getProperties());
		if (username != null) {
			properties.setProperty(USERNAME_KEY, username);
		}
		if (password != null) {
			properties.setProperty(PASSWORD_KEY, password);
		}
		return new ImmutableProperties(properties);
	}

	protected String toNull(String token, String nullToken) {
		Assert.notNull(nullToken);
		return StringUtils.equals(token, nullToken) ? null : token;
	}
}
