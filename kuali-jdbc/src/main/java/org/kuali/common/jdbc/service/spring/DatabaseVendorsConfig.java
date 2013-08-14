package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.Vendor;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.sql.AdminSql;
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
public class DatabaseVendorsConfig {

	@Configuration
	@Oracle
	@Import({ SpringServiceConfig.class })
	public static class OracleConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final String DEFAULT_URL = "jdbc:oracle:thin:@localhost:1521:XE";
		public static final Vendor VENDOR = Vendor.ORACLE;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", DEFAULT_URL);
			Class<? extends Driver> driver = getDriver(env, VENDOR, OracleDriver.class);
			ConnectionContext dba = getDbaContext(env, VENDOR, "system", "manager", DEFAULT_URL);
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

		public static final String DEFAULT_URL = "jdbc:mysql://localhost";
		public static final Vendor VENDOR = Vendor.MYSQL;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, VENDOR, "url", DEFAULT_URL) + "/" + env.getString("jdbc.username");
			Class<? extends Driver> driver = getDriver(env, VENDOR, com.mysql.jdbc.Driver.class);
			ConnectionContext dba = getDbaContext(env, VENDOR, "root", NullUtils.NONE, DEFAULT_URL);
			AdminSql sql = getAdminSql(env, VENDOR);
			String dbaAfter = getString(env, VENDOR, "dba.after");
			return new DatabaseVendor(VENDOR, dba, url, driver, sql, dbaAfter);
		}

	}

	protected static Class<? extends Driver> getDriver(EnvironmentService env, Vendor vendor, Class<? extends Driver> defaultClass) {
		String key = vendor.name().toLowerCase() + ".driver";
		return env.getClass(key, Driver.class, defaultClass);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix) {
		return getString(env, vendor, suffix);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix, String defaultValue) {
		String prefix = vendor.name().toLowerCase();
		String key = prefix + "." + suffix;
		return env.getString(key, defaultValue);
	}

	protected static ConnectionContext getDbaContext(EnvironmentService env, Vendor vendor, String usr, String pwd, String url) {
		String prefix = vendor.name().toLowerCase();
		String dbaUsr = env.getString(prefix + ".dba.username", usr);
		String dbaPwd = env.getString(prefix + ".dba.password", pwd);
		String dbaUrl = env.getString(prefix + ".dba.url", url);
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected static AdminSql getAdminSql(EnvironmentService env, Vendor vendor) {
		String prefix = vendor.name().toLowerCase();
		String validate = env.getString(prefix + ".validate");
		String create = env.getString(prefix + ".create");
		String drop = env.getString(prefix + ".drop");
		return new AdminSql(validate, create, drop);
	}
}
