package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.VendorName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcVendorConfig {

	@Bean
	public VendorName oracleVendor() {
		return null;
	}

}
