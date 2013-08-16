package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.JdbcConnections;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
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
		ConnectionContext normal = getNormal();
		ConnectionContext dba = getDba();
		return new JdbcConnections(normal, dba);
	}

	protected ConnectionContext getNormal() {
		String username = env.getString("jdbc.username"); // No default value. They must supply jdbc.username
		String password = env.getString("jdbc.password", username);
		String url = env.getString("jdbc.url", vendor.getUrl());
		return new ConnectionContext(url, username, password);
	}

	protected ConnectionContext getDba() {
		Credentials auth = vendor.getDba().getCredentials();
		String username = env.getString("jdbc.dba.username", auth.getUsername());
		String password = env.getString("jdbc.dba.password", auth.getPassword());
		String url = env.getString("jdbc.dba.url", vendor.getDba().getUrl());
		return new ConnectionContext(url, username, password);
	}
}
