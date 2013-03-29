package org.kuali.common.jdbc.spring;

import org.springframework.core.env.Environment;

public class JdbcConfigContext {

	Environment env;
	String fragment;
	JdbcCommonConfig commonConfig;
	JdbcDataSourceConfig dataSourceConfig;

	public JdbcConfigContext() {
		this(null, null, null, null);
	}

	public JdbcConfigContext(Environment env, String fragment, JdbcCommonConfig commonConfig, JdbcDataSourceConfig dataSourceConfig) {
		super();
		this.env = env;
		this.fragment = fragment;
		this.commonConfig = commonConfig;
		this.dataSourceConfig = dataSourceConfig;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
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

}
