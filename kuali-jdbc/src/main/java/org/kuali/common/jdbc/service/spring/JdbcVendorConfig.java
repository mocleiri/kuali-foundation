package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcVendorConfig {

	@Bean
	public Vendor oracleVendor() {
		return null;
	}

}
