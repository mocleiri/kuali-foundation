package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.Vendors;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.service.spring.annotation.MySql;
import org.kuali.common.jdbc.service.spring.annotation.Oracle;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class SqlContextsConfig {

	@Configuration
	@Oracle
	@Import({ SpringServiceConfig.class })
	public static class OracleConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final String DEFAULT_URL = "jdbc:oracle:thin:@localhost:1521:XE";

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String dbaUsr = env.getString("oracle.dba.username", "system");
			String dbaPwd = env.getString("oracle.dba.password", "manager");
			String dbaUrl = env.getString("oracle.dba.url", DEFAULT_URL);
			String regUrl = env.getString("oracle.url", DEFAULT_URL);
			Class<? extends Driver> driver = env.getClass("oracle.driver", Driver.class, OracleDriver.class);
			ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
			return new DatabaseVendor(Vendors.ORACLE, dba, regUrl, driver);
		}
	}

	@Configuration
	@MySql
	@Import({ SpringServiceConfig.class })
	public static class MySqlConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final String DEFAULT_URL = "jdbc:mysql://localhost";

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String dbaUsr = env.getString("mysql.dba.username", "root");
			String dbaPwd = env.getString("mysql.dba.password", NullUtils.NONE);
			String dbaUrl = env.getString("mysql.dba.url", DEFAULT_URL);
			String regUrl = env.getString("mysql.url", DEFAULT_URL) + "/" + env.getString("jdbc.username");
			Class<? extends Driver> driver = env.getClass("mysql.driver", Driver.class, com.mysql.jdbc.Driver.class);
			ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
			return new DatabaseVendor(Vendors.MYSQL, dba, regUrl, driver);
		}

	}
}
