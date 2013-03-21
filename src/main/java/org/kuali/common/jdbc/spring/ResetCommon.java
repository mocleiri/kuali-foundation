package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ResetCommon {

	@Autowired
	Environment env;

	@Bean
	public SqlReader sqlReader() {
		return new DefaultSqlReader();
	}

}
