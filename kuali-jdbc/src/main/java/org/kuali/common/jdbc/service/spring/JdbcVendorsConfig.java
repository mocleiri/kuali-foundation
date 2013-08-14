package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.VendorName;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
public class JdbcVendorsConfig {

	@Import({ SpringServiceConfig.class })
	@Profile("oracle")
	public static class OracleDatabaseVendor {

		@Autowired
		EnvironmentService env;

		@Bean
		public DatabaseVendor databaseVendor() {
			VendorName name = VendorName.ORACLE;
			String dbaUsr = env.getString("oracle.dba.username", Dba.USERNAME);
			String dbaPwd = env.getString("oracle.dba.password", Dba.PASSWORD);
			String dbaUrl = env.getString("oracle.dba.url", Dba.URL);
			ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
			Class<? extends Driver> driver = env.getClass("oracle.driver", DRIVER);
			String url = env.getString("oracle.url", URL);
			return new DatabaseVendor(name, dba, url, driver);
		}

		public static final Class<? extends Driver> DRIVER = OracleDriver.class;
		public static final String URL = Dba.URL;

		public static class Dba {
			public static final String USERNAME = "system";
			public static final String PASSWORD = "manager";
			public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		}
	}

	@Import({ SpringServiceConfig.class })
	@Profile("mysql")
	public static class MySqlDatabaseVendor {

		@Autowired
		EnvironmentService env;

		@Bean
		public DatabaseVendor databaseVendor() {
			VendorName name = VendorName.MYSQL;
			String dbaUsr = env.getString("mysql.dba.username", Dba.USERNAME);
			String dbaPwd = env.getString("mysql.dba.password", Dba.PASSWORD);
			String dbaUrl = env.getString("mysql.dba.url", Dba.URL);
			ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
			Class<? extends Driver> driver = env.getClass("mysql.driver", DRIVER);
			String url = env.getString("mysql.url", URL);
			return new DatabaseVendor(name, dba, url, driver);
		}

		public static final Class<? extends Driver> DRIVER = com.mysql.jdbc.Driver.class;
		public static final String URL = Dba.URL + "/${jdbc.username}";

		public static class Dba {
			public static final String USERNAME = "root";
			public static final String PASSWORD = NullUtils.NONE;
			public static final String URL = "jdbc:mysql://localhost";
		}
	}
}
