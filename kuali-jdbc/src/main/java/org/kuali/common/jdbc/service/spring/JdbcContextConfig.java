package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.sql.JdbcContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DatabaseVendorsConfig.class })
public class JdbcContextConfig {

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	EnvironmentService env;

	@Bean
	public JdbcContext jdbcContext() {

		ConnectionContext normal = getConnectionContext("jdbc.username", "jdbc.password", "jdbc.url");
		ConnectionContext dba = getConnectionContext("jdbc.dba.username", "jdbc.dba.password", "jdbc.dba.url");

		return new JdbcContext(normal, dba);
	}

	protected ConnectionContext getConnectionContext(String usernameKey, String passwordKey, String urlKey) {
		String username = env.getString(usernameKey);
		String password = env.getString(passwordKey);
		String url = env.getString(urlKey);
		return new ConnectionContext(url, username, password);
	}
}
