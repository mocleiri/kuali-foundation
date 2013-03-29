package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.context.SqlContext;
import org.springframework.core.env.Environment;

public class JdbcConfigContext {

	Environment env;
	SqlContext sqlContext;
	JdbcCommonConfig commonConfig;
	JdbcDataSourceConfig dataSourceConfig;

	public JdbcConfigContext() {
		this(null, null, null, null);
	}

	public JdbcConfigContext(Environment env, SqlContext sqlContext, JdbcCommonConfig commonConfig, JdbcDataSourceConfig dataSourceConfig) {
		super();
		this.env = env;
		this.sqlContext = sqlContext;
		this.commonConfig = commonConfig;
		this.dataSourceConfig = dataSourceConfig;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public JdbcCommonConfig getCommonConfig() {
		return commonConfig;
	}

	public void setCommonConfig(JdbcCommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	public JdbcDataSourceConfig getDataSourceConfig() {
		return dataSourceConfig;
	}

	public void setDataSourceConfig(JdbcDataSourceConfig dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
	}

	public SqlContext getSqlContext() {
		return sqlContext;
	}

	public void setSqlContext(SqlContext sqlContext) {
		this.sqlContext = sqlContext;
	}

}
