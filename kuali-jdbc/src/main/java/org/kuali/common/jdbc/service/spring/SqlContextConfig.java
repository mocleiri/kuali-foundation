package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.DatabaseVendor;
import org.kuali.common.jdbc.model.sql.SqlContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DatabaseVendorsConfig.class })
public class SqlContextConfig {

	@Autowired
	DatabaseVendor databaseVendor;

	@Bean
	public SqlContext sqlContext() {

		return null;
	}

}
