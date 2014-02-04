package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.JdbcConnections;
import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.sql.spring.SqlContextConfig;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcConnectionsConfig.class, SqlContextConfig.class })
public class DatabaseProcessContextConfig {

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	JdbcConnections connections;

	@Autowired
	SqlContext sql;

	@Bean
	public DatabaseProcessContext databaseProcessContext() {
		return new DatabaseProcessContext(vendor, connections, sql.getEncoding(), sql.getSchema());
	}

}
