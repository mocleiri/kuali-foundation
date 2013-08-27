package org.kuali.common.jdbc.vendor.spring;

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.VendorDefault;
import org.kuali.common.jdbc.vendor.service.DatabaseVendorService;
import org.kuali.common.jdbc.vendor.service.MySqlDatabaseVendorService;
import org.kuali.common.jdbc.vendor.service.OracleDatabaseVendorService;
import org.kuali.common.util.Assert;
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
	public DatabaseVendorService databaseVendorService() {
		String vendorString = env.getString(VENDOR_KEY); // No default value. They must supply db.vendor
		Assert.noBlanks(vendorString);
		VendorDefault vendor = VendorDefault.valueOf(vendorString.toUpperCase());
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
