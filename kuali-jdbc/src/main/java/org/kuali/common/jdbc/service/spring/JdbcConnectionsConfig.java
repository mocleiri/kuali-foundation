package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.JdbcConnections;
import org.kuali.common.jdbc.sql.model.JdbcKeys;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DatabaseVendorConfig.class })
public class JdbcConnectionsConfig {

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	EnvironmentService env;

	@Bean
	public JdbcConnections jdbcConnections() {
		Class<? extends Driver> driver = getDriver(env, vendor.getDriver());
		return new JdbcConnections(getNormal(), getDba(), driver);
	}

	protected Class<? extends Driver> getDriver(EnvironmentService env, Class<? extends Driver> defaultValue) {
		String driver = env.getString(JdbcKeys.DRIVER.getValue(), defaultValue.getName());
		return ReflectionUtils.getTypedClass(driver);
	}

	protected ConnectionContext getNormal() {
		String username = env.getString(JdbcKeys.USERNAME.getValue()); // No default value. They must supply jdbc.username
		String password = env.getString(JdbcKeys.PASSWORD.getValue(), username);
		String url = env.getString(JdbcKeys.URL.getValue(), vendor.getUrl());
		return new ConnectionContext(url, username, password);
	}

	protected ConnectionContext getDba() {
		Credentials auth = vendor.getDba().getCredentials();
		String username = env.getString(JdbcKeys.DBA_USERNAME.getValue(), auth.getUsername());
		String password = env.getString(JdbcKeys.DBA_PASSWORD.getValue(), auth.getPassword());
		String url = env.getString(JdbcKeys.DBA_URL.getValue(), vendor.getDba().getUrl());
		return new ConnectionContext(url, username, password);
	}
}
