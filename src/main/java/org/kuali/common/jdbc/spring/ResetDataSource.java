package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ResetDataSource {

	@Autowired
	Environment env;

	@Bean
	public DatabaseProcessContext jdbcDatabaseProcessContext() {
		DatabaseProcessContext dpc = new DatabaseProcessContext();
		dpc.setVendor(SpringUtils.getProperty(env, "db.vendor"));
		dpc.setUrl(SpringUtils.getProperty(env, "jdbc.url"));
		dpc.setUsername(SpringUtils.getProperty(env, "jdbc.username"));
		dpc.setPassword(SpringUtils.getProperty(env, "jdbc.password"));
		dpc.setDbaUrl(SpringUtils.getProperty(env, "jdbc.dba.url"));
		dpc.setDbaUsername(SpringUtils.getProperty(env, "jdbc.dba.username"));
		dpc.setDbaPassword(SpringUtils.getProperty(env, "jdbc.dba.password"));
		dpc.setEncoding(SpringUtils.getProperty(env, "sql.encoding"));
		return dpc;
	}
}
