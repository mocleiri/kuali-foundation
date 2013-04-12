package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.GroupedSqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public abstract class ResetBaseConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;
}
