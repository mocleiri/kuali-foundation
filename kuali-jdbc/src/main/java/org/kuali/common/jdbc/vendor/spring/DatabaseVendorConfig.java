package org.kuali.common.jdbc.vendor.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.jdbc.vendor.service.DatabaseVendorService;
import org.kuali.common.jdbc.vendor.service.MySqlDatabaseVendorService;
import org.kuali.common.jdbc.vendor.service.OracleDatabaseVendorService;
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
	public DatabaseVendorService databaseVendorService() {
		Vendor vendor = databaseVendorEnum();
		VendorBase base = vendorBaseMap().get(vendor);
		switch (vendor) {
		case ORACLE:
			return new OracleDatabaseVendorService(env, base);
		case MYSQL:
			return new MySqlDatabaseVendorService(env, base);
		default:
			throw new IllegalStateException("Vendor [" + vendor + "] is unknown");

		}
	}

	@Bean
	public DatabaseVendor databaseVendor() {
		return databaseVendorService().getDatabaseVendor();
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
}
