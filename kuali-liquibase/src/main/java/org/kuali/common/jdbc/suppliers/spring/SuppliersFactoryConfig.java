package org.kuali.common.jdbc.suppliers.spring;

import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.reader.spring.SqlReaderConfig;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.sql.spring.SqlContextConfig;
import org.kuali.common.jdbc.suppliers.ResourcesSupplierFactory;
import org.kuali.common.jdbc.suppliers.SqlLocationContext;
import org.kuali.common.jdbc.suppliers.SqlSupplierFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SqlReaderConfig.class, SqlContextConfig.class })
public class SuppliersFactoryConfig {

	@Autowired
	SqlReader reader;

	@Autowired
	SqlContext context;

	@Bean
	public SqlSupplierFactory sqlSupplierFactory() {
		return new SqlSupplierFactory(new SqlLocationContext(context.getEncoding(), reader));
	}

	@Bean
	public ResourcesSupplierFactory resourcesSuppliersFactory() {
		return new ResourcesSupplierFactory(sqlSupplierFactory());
	}
}
