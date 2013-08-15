package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.service.spring.annotation.MySql;
import org.kuali.common.jdbc.service.spring.annotation.Oracle;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorDefaults;
import org.kuali.common.util.ReflectionUtils;
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

		public static final VendorDefaults DEFAULTS = VendorDefaults.ORACLE;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, DEFAULTS.getVendor(), "url", DEFAULTS.getDba().getUrl());
			return getDatabaseVendor(env, DEFAULTS, url);
		}
	}

	@Configuration
	@MySql
	@Import({ SpringServiceConfig.class })
	public static class MySqlConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final VendorDefaults DEFAULTS = VendorDefaults.ORACLE;

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, DEFAULTS.getVendor(), "url", DEFAULTS.getDba().getUrl()) + "/" + env.getString("jdbc.username");
			return getDatabaseVendor(env, DEFAULTS, url);
		}

	}

	public static DatabaseVendor getDatabaseVendor(EnvironmentService env, VendorDefaults defaults, String url) {
		Vendor vendor = defaults.getVendor();
		Class<? extends Driver> driver = getDriver(env, defaults);
		ConnectionContext dba = getDbaContext(env, defaults);
		AdminSql sql = getAdminSql(env, vendor);
		String dbaAfter = getString(env, vendor, "dba.after", null);
		return new DatabaseVendor(vendor, dba, url, driver, sql, dbaAfter);
	}

	protected static Class<? extends Driver> getDriver(EnvironmentService env, VendorDefaults defaults) {
		Class<? extends Driver> defaultClass = ReflectionUtils.newInstance(defaults.getDriver());
		String code = defaults.getVendor().getCode();
		return env.getClass(code + ".driver", Driver.class, defaultClass);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix, String defaultValue) {
		return env.getString(vendor.getCode() + "." + suffix, defaultValue);
	}

	protected static ConnectionContext getDbaContext(EnvironmentService env, VendorDefaults defaults) {
		Credentials auth = defaults.getDba().getCredentials();
		Vendor vendor = defaults.getVendor();
		String dbaUsr = env.getString(vendor.getCode() + ".dba.username", auth.getUsername());
		String dbaPwd = env.getString(vendor.getCode() + ".dba.password", auth.getPassword());
		String dbaUrl = env.getString(vendor.getCode() + ".dba.url", defaults.getDba().getUrl());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected static AdminSql getAdminSql(EnvironmentService env, Vendor vendor) {
		String validate = env.getString(vendor.getCode() + ".validate");
		String create = env.getString(vendor.getCode() + ".create");
		String drop = env.getString(vendor.getCode() + ".drop");
		return new AdminSql(validate, create, drop);
	}
}
