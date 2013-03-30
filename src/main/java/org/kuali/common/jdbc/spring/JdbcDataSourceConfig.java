package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import javax.sql.DataSource;

import org.kuali.common.jdbc.ShowConfigExecutable;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.nullify.DefaultBeanNullifier;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Import(JdbcCommonConfig.class)
public class JdbcDataSourceConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Bean
	public DatabaseProcessContext jdbcDatabaseProcessContext() {
		DatabaseProcessContext ctx = new DatabaseProcessContext();
		ctx.setVendor(SpringUtils.getProperty(env, "db.vendor"));
		ctx.setDriver(SpringUtils.getProperty(env, "jdbc.driver"));
		ctx.setUrl(SpringUtils.getProperty(env, "jdbc.url"));
		ctx.setUsername(SpringUtils.getProperty(env, "jdbc.username"));
		ctx.setPassword(SpringUtils.getProperty(env, "jdbc.password"));
		ctx.setDbaUrl(SpringUtils.getProperty(env, "jdbc.dba.url"));
		ctx.setDbaUsername(SpringUtils.getProperty(env, "jdbc.dba.username"));
		ctx.setDbaPassword(SpringUtils.getProperty(env, "jdbc.dba.password"));
		ctx.setEncoding(SpringUtils.getProperty(env, "sql.encoding"));

		DefaultBeanNullifier nullifier = new DefaultBeanNullifier();
		nullifier.setBean(ctx);
		nullifier.setNullTokens(Arrays.asList(Constants.NONE, Constants.NULL));
		nullifier.setProperties(Arrays.asList("username", "password", "dbaUsername", "dbaPassword"));

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

	@Bean
	public Executable jdbcShowConfigExecutable() {
		ShowConfigExecutable sce = new ShowConfigExecutable();
		sce.setService(commonConfig.jdbcService());
		sce.setContext(jdbcDatabaseProcessContext());
		sce.setDataSource(jdbcDbaDataSource());
		sce.setSkip(SpringUtils.getBoolean(env, "jdbc.showconfig.skip", false));
		return sce;
	}
}
