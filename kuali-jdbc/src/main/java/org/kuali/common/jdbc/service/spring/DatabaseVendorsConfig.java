package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.service.spring.annotation.MySql;
import org.kuali.common.jdbc.service.spring.annotation.Oracle;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class DatabaseVendorsConfig {

	@Configuration
	@Oracle
	@Import({ SpringServiceConfig.class })
	public static class OracleConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final Vendor VENDOR = Vendor.ORACLE;
		public static final Class<? extends Driver> DEFAULT_DRIVER = OracleDriver.class;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", VENDOR.getDba().getUrl());
			return getDatabaseVendor(env, VENDOR, url, DEFAULT_DRIVER);
		}
	}

	@Configuration
	@MySql
	@Import({ SpringServiceConfig.class })
	public static class MySqlConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final Vendor VENDOR = Vendor.MYSQL;
		public static final Class<? extends Driver> DEFAULT_DRIVER = com.mysql.jdbc.Driver.class;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", VENDOR.getDba().getUrl()) + "/" + env.getString("jdbc.username");
			return getDatabaseVendor(env, VENDOR, url, DEFAULT_DRIVER);
		}

	}

	public static DatabaseVendor getDatabaseVendor(EnvironmentService env, Vendor vendor, String url, Class<? extends Driver> defaultDriver) {
		Class<? extends Driver> driver = getDriver(env, vendor, defaultDriver);
		ConnectionContext dba = getDbaContext(env, vendor);
		AdminSql sql = getAdminSql(env, vendor);
		String dbaAfter = getString(env, vendor, "dba.after", null);
		return new DatabaseVendor(vendor, dba, url, driver, sql, dbaAfter);
	}

	protected static Class<? extends Driver> getDriver(EnvironmentService env, Vendor vendor, Class<? extends Driver> defaultClass) {
		return env.getClass(vendor.getCode() + ".driver", Driver.class, defaultClass);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix, String defaultValue) {
		return env.getString(vendor.getCode() + "." + suffix, defaultValue);
	}

	protected static ConnectionContext getDbaContext(EnvironmentService env, Vendor vendor) {
		String dbaUsr = env.getString(vendor.getCode() + ".dba.username", vendor.getDba().getUsername());
		String dbaPwd = env.getString(vendor.getCode() + ".dba.password", vendor.getDba().getPassword());
		String dbaUrl = env.getString(vendor.getCode() + ".dba.url", vendor.getDba().getUrl());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected static AdminSql getAdminSql(EnvironmentService env, Vendor vendor) {
		String validate = env.getString(vendor.getCode() + ".validate");
		String create = env.getString(vendor.getCode() + ".create");
		String drop = env.getString(vendor.getCode() + ".drop");
		return new AdminSql(validate, create, drop);
	}
}
