package org.kuali.common.util.spring.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DefaultDatabaseConfig.class, Oracle11gDatabaseConfig.class, OracleDatabaseConfig.class })
public class DatabaseConfig {

	@Autowired
	@Qualifier(DatabaseConstants.VENDOR_BEAN_NAME)
	String vendor;

	@Bean
	public Database databaseConfigDatabase() {
		DefaultDatabase dd = new DefaultDatabase();
		dd.setVendor(vendor);
		return dd;
	}

}
