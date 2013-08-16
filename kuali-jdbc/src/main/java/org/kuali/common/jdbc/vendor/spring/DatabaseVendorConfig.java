package org.kuali.common.jdbc.vendor.spring;

import java.sql.Driver;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.KeySuffix;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DatabaseVendorConfig {

	private static final String VENDOR_KEY = "db.vendor";

	@Autowired
	EnvironmentService env;

	@Bean
	public Vendor databaseVendorEnum() {
		String vendor = env.getString(VENDOR_KEY);
		return Vendor.valueOf(vendor.toUpperCase());
	}

	@Bean
	public DatabaseVendor databaseVendor() {
		Vendor vendor = databaseVendorEnum();
		VendorBase base = vendorBaseMap().get(vendor);
		ConnectionContext dba = getDba(base);
		Class<? extends Driver> driver = getDriver(base);
		Properties sql = getSql(base);
		return new DatabaseVendor(databaseVendorEnum(), dba, null, driver, sql);
	}

	protected Properties getSql(VendorBase base) {
		Vendor vendor = base.getVendor();
		List<KeySuffix> suffixes = vendorSqlKeysMap().get(vendor);
		Properties properties = new Properties();
		for (KeySuffix suffix : suffixes) {
			String key = vendor.getCode() + "." + suffix.getKeySuffix();
			String sql = env.getString(key);
			properties.setProperty(key, sql);
		}
		return properties;
	}

	protected Class<? extends Driver> getDriver(VendorBase base) {
		String prefix = base.getVendor().getCode();
		String driver = env.getString(prefix + ".driver", base.getDriver());
		return ReflectionUtils.newInstance(driver);
	}

	protected ConnectionContext getDba(VendorBase base) {
		String prefix = base.getVendor().getCode();
		String dbaUrl = env.getString(prefix + ".dba.url", base.getDba().getUrl());
		String dbaUsr = env.getString(prefix + ".dba.username", base.getDba().getCredentials().getUsername());
		String dbaPwd = env.getString(prefix + ".dba.password", base.getDba().getCredentials().getPassword());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	@Bean
	public Map<Vendor, VendorBase> vendorBaseMap() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Vendors.Oracle.DBA, Vendors.Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, Vendors.MySql.DBA, Vendors.MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

	@Bean
	public Map<Vendor, List<KeySuffix>> vendorSqlKeysMap() {
		List<KeySuffix> oracle = CollectionUtils.combine(Admin.asList(), Liquibase.asList(), Oracle.asList());
		List<KeySuffix> mysql = CollectionUtils.combine(Admin.asList(), Liquibase.asList());
		Map<Vendor, List<KeySuffix>> map = new HashMap<Vendor, List<KeySuffix>>();
		map.put(Vendor.ORACLE, Collections.unmodifiableList(oracle));
		map.put(Vendor.MYSQL, Collections.unmodifiableList(mysql));
		return Collections.unmodifiableMap(map);
	}

}
