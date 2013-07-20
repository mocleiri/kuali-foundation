package org.kuali.common.util.spring.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DefaultDatabaseConfig.class, OracleDatabaseConfig.class })
public class DatabaseConfig implements DatabaseConfigSupplier {

	@Autowired
	@Qualifier(DatabaseConstants.VENDOR_BEAN_NAME)
	String vendor;

	@Override
	public String getVendor() {
		return vendor;
	}

}
