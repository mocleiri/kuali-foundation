package org.kuali.common.jdbc.reader.spring;

import org.kuali.common.jdbc.reader.DefaultSqlReader;
import org.kuali.common.jdbc.reader.SqlReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlReaderConfig {

	@Bean
	public SqlReader sqlReader() {
		return new DefaultSqlReader();
	}

}
