/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import javax.sql.DataSource;

import org.kuali.common.jdbc.ShowConfigExecutable;
import org.kuali.common.jdbc.ShowDbaConfigExecutable;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.nullify.BeanNullifier;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import(JdbcCommonConfig.class)
public class JdbcDataSourceConfig {

	// Property keys
	protected static final String VENDOR_KEY = "db.vendor";
	protected static final String DRIVER_KEY = "jdbc.driver";
	protected static final String URL_KEY = "jdbc.url";
	protected static final String USERNAME_KEY = "jdbc.username";
	protected static final String PASSWORD_KEY = "jdbc.password";
	protected static final String DBA_URL_KEY = "jdbc.dba.url";
	protected static final String DBA_USERNAME_KEY = "jdbc.dba.username";
	protected static final String DBA_PASSWORD_KEY = "jdbc.dba.password";
	protected static final String ENCODING_KEY = "sql.encoding";
	protected static final String SCHEMA_KEY = "sql.schema";
	protected static final String SHOW_CONFIG_SKIP_KEY = "jdbc.showconfig.skip";

	// Default values
	protected static final boolean DEFAULT_SHOW_CONFIG_SKIP = false;
	protected static final String NULLIFIED_CONTEXT_PROPERTIES_CSV = "username,password,dbaUsername,dbaPassword";

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Bean
	public DatabaseProcessContext jdbcDatabaseProcessContext() {
		DatabaseProcessContext ctx = new DatabaseProcessContext();
		ctx.setVendor(SpringUtils.getProperty(env, VENDOR_KEY));
		ctx.setDriver(SpringUtils.getProperty(env, DRIVER_KEY));
		ctx.setUrl(SpringUtils.getProperty(env, URL_KEY));
		ctx.setUsername(SpringUtils.getProperty(env, USERNAME_KEY));
		ctx.setPassword(SpringUtils.getProperty(env, PASSWORD_KEY));
		ctx.setDbaUrl(SpringUtils.getProperty(env, DBA_URL_KEY));
		ctx.setDbaUsername(SpringUtils.getProperty(env, DBA_USERNAME_KEY));
		ctx.setDbaPassword(SpringUtils.getProperty(env, DBA_PASSWORD_KEY));
		ctx.setEncoding(SpringUtils.getProperty(env, ENCODING_KEY));
		ctx.setSchema(SpringUtils.getProperty(env, SCHEMA_KEY));

		BeanNullifier nullifier = new BeanNullifier();
		nullifier.setBean(ctx);
		nullifier.setNullTokens(Arrays.asList(NullUtils.NONE, NullUtils.NULL));
		nullifier.setProperties(CollectionUtils.getTrimmedListFromCSV(NULLIFIED_CONTEXT_PROPERTIES_CSV));

		// Null out usernames/passwords that are set to NONE or NULL
		nullifier.nullify();

		return ctx;
	}

	@Bean
	public DataSource jdbcDataSource() {
		DatabaseProcessContext ctx = jdbcDatabaseProcessContext();
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(ctx.getDriver());
		dmds.setUrl(ctx.getUrl());
		dmds.setUsername(ctx.getUsername());
		dmds.setPassword(ctx.getPassword());
		return dmds;
	}

	@Bean
	public DataSource jdbcDbaDataSource() {
		DatabaseProcessContext ctx = jdbcDatabaseProcessContext();
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(ctx.getDriver());
		dmds.setUrl(ctx.getDbaUrl());
		dmds.setUsername(ctx.getDbaUsername());
		dmds.setPassword(ctx.getDbaPassword());
		return dmds;
	}

	/**
	 * This bean requires DBA credentials
	 */
	@Bean
	public Executable jdbcShowDbaConfigExecutable() {
		ShowDbaConfigExecutable exec = new ShowDbaConfigExecutable();
		exec.setService(commonConfig.jdbcService());
		exec.setContext(jdbcDatabaseProcessContext());
		exec.setDataSource(jdbcDbaDataSource());
		exec.setSkip(SpringUtils.getBoolean(env, SHOW_CONFIG_SKIP_KEY, DEFAULT_SHOW_CONFIG_SKIP));
		return exec;
	}

	@Bean
	public Executable jdbcShowConfigExecutable() {
		ShowConfigExecutable exec = new ShowConfigExecutable();
		exec.setService(commonConfig.jdbcService());
		exec.setContext(jdbcDatabaseProcessContext());
		exec.setDataSource(jdbcDataSource());
		exec.setSkip(SpringUtils.getBoolean(env, SHOW_CONFIG_SKIP_KEY, DEFAULT_SHOW_CONFIG_SKIP));
		return exec;
	}
}
