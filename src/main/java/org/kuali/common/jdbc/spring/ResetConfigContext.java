package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.context.SqlMode;
import org.springframework.core.env.Environment;

public class ResetConfigContext {

	Environment env;
	String type;
	SqlMode mode;
	JdbcCommonConfig commonConfig;
	JdbcDataSourceConfig dataSourceConfig;

	public ResetConfigContext() {
		this(null, null, null, null, null);
	}

	public ResetConfigContext(Environment env, String type, SqlMode mode, JdbcCommonConfig commonConfig, JdbcDataSourceConfig dataSourceConfig) {
		super();
		this.env = env;
		this.type = type;
		this.mode = mode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SqlMode getMode() {
		return mode;
	}

	public void setMode(SqlMode mode) {
		this.mode = mode;
	}

}
