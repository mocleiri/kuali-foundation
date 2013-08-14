package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.Vendor;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.sql.AdminSql;
import org.kuali.common.jdbc.service.spring.annotation.MySql;
import org.kuali.common.jdbc.service.spring.annotation.Oracle;
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

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", VENDOR.getDba().getUrl());
			Class<? extends Driver> driver = getDriver(env, VENDOR, OracleDriver.class);
			ConnectionContext dba = getDbaContext(env, VENDOR);
			AdminSql sql = getAdminSql(env, VENDOR);
			String dbaAfter = getString(env, VENDOR, "dba.after");
			return new DatabaseVendor(VENDOR, dba, url, driver, sql, dbaAfter);
		}
	}

	@Configuration
	@MySql
	@Import({ SpringServiceConfig.class })
	public static class MySqlConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final Vendor VENDOR = Vendor.MYSQL;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", VENDOR.getDba().getUrl()) + "/" + env.getString("jdbc.username");
			Class<? extends Driver> driver = getDriver(env, VENDOR, com.mysql.jdbc.Driver.class);
			ConnectionContext dba = getDbaContext(env, VENDOR);
			AdminSql sql = getAdminSql(env, VENDOR);
			String dbaAfter = getString(env, VENDOR, "dba.after");
			return new DatabaseVendor(VENDOR, dba, url, driver, sql, dbaAfter);
		}

	}

	protected static Class<? extends Driver> getDriver(EnvironmentService env, Vendor vendor, Class<? extends Driver> defaultClass) {
		return env.getClass(vendor.getCode() + ".driver", Driver.class, defaultClass);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix) {
		return getString(env, vendor, suffix);
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
