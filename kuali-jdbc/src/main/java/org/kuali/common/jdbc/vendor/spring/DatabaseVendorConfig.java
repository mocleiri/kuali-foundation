package org.kuali.common.jdbc.vendor.spring;

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
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
		switch (vendor) {
		case ORACLE:
			return new OracleDatabaseVendorService(env, vendor);
		case MYSQL:
			return new MySqlDatabaseVendorService(env, vendor);
		default:
			throw new IllegalStateException("Vendor [" + vendor + "] is unknown");

		}
	}

	@Bean
	public DatabaseVendor databaseVendor() {
		return databaseVendorService().getDatabaseVendor();
	}

}
