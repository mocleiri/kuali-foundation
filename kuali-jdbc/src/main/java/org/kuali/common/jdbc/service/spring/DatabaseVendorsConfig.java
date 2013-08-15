package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.service.spring.annotation.MySql;
import org.kuali.common.jdbc.service.spring.annotation.Oracle;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.util.PropertyUtils;
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

		public static final VendorBase BASE = Vendors.DEFAULTS.get(Vendors.Profiles.ORACLE);

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, BASE.getVendor(), "url", BASE.getDba().getUrl());
			return getDatabaseVendor(env, BASE, url);
		}
	}

	@Configuration
	@MySql
	@Import({ SpringServiceConfig.class })
	public static class MySqlConfig implements DatabaseVendorConfig {

		@Autowired
		EnvironmentService env;

		public static final VendorBase BASE = Vendors.DEFAULTS.get(Vendors.Profiles.MYSQL);

		@Override
		@Bean
		public DatabaseVendor databaseVendor() {
			String url = getString(env, BASE.getVendor(), "url", BASE.getDba().getUrl()) + "/" + env.getString("jdbc.username");
			return getDatabaseVendor(env, BASE, url);
		}

	}

	public static Properties getSql(EnvironmentService env, Vendor vendor) {
		List<String> keys = Vendors.DEFAULT_SQL_KEYS;
		Properties props = new Properties();
		for (String key : keys) {
			String fullKey = vendor.getCode() + "." + key;
			String sql = env.getString(fullKey);
			props.setProperty(fullKey, sql);
		}
		return PropertyUtils.toImmutable(props);
	}

	public static DatabaseVendor getDatabaseVendor(EnvironmentService env, VendorBase base, String url) {
		Vendor vendor = base.getVendor();
		Class<? extends Driver> driver = getDriver(env, base);
		ConnectionContext dba = getDbaContext(env, base);
		Properties sql = getSql(env, vendor);
		return new DatabaseVendor(vendor, dba, url, driver, sql);
	}

	protected static Class<? extends Driver> getDriver(EnvironmentService env, VendorBase base) {
		Class<? extends Driver> defaultClass = ReflectionUtils.newInstance(base.getDriver());
		String code = base.getVendor().getCode();
		return env.getClass(code + ".driver", Driver.class, defaultClass);
	}

	protected static String getString(EnvironmentService env, Vendor vendor, String suffix, String defaultValue) {
		return env.getString(vendor.getCode() + "." + suffix, defaultValue);
	}

	protected static ConnectionContext getDbaContext(EnvironmentService env, VendorBase base) {
		Credentials auth = base.getDba().getCredentials();
		Vendor vendor = base.getVendor();
		String dbaUsr = env.getString(vendor.getCode() + ".dba.username", auth.getUsername());
		String dbaPwd = env.getString(vendor.getCode() + ".dba.password", auth.getPassword());
		String dbaUrl = env.getString(vendor.getCode() + ".dba.url", base.getDba().getUrl());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

}
