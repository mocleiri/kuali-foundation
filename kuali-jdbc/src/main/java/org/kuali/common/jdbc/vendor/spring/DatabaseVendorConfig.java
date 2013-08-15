package org.kuali.common.jdbc.vendor.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.KeySuffix;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.CollectionUtils;
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
		VendorBase base = vendorBaseMap().get(databaseVendorEnum());
		return new DatabaseVendor(null, null, null, null, null);
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
		List<KeySuffix> oracle = Collections.unmodifiableList(CollectionUtils.combine(Admin.asList(), Liquibase.asList(), Oracle.asList()));
		List<KeySuffix> mysql = Collections.unmodifiableList(CollectionUtils.combine(Admin.asList(), Liquibase.asList()));
		Map<Vendor, List<KeySuffix>> map = new HashMap<Vendor, List<KeySuffix>>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

}
