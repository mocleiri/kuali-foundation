package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import javax.sql.DataSource;

import org.kuali.common.jdbc.ShowConfigExecutable;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.nullify.DefaultBeanNullifier;
import org.kuali.common.util.nullify.Nullify;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Import(ResetCommon.class)
public class ResetDataSource {

	@Autowired
	Environment env;

	@Autowired
	ResetCommon resetCommon;

	@Bean
	public DatabaseProcessContext jdbcDatabaseProcessContext() {
		DatabaseProcessContext dpc = new DatabaseProcessContext();
		dpc.setVendor(SpringUtils.getProperty(env, "db.vendor"));
		dpc.setDriver(SpringUtils.getProperty(env, "jdbc.driver"));
		dpc.setUrl(SpringUtils.getProperty(env, "jdbc.url"));
		dpc.setUsername(SpringUtils.getProperty(env, "jdbc.username"));
		dpc.setPassword(SpringUtils.getProperty(env, "jdbc.password"));
		dpc.setDbaUrl(SpringUtils.getProperty(env, "jdbc.dba.url"));
		dpc.setDbaUsername(SpringUtils.getProperty(env, "jdbc.dba.username"));
		dpc.setDbaPassword(SpringUtils.getProperty(env, "jdbc.dba.password"));
		dpc.setEncoding(SpringUtils.getProperty(env, "sql.encoding"));
		return dpc;
	}

	@Bean
	public Nullify jdbcNullifier() {
		DefaultBeanNullifier dbn = new DefaultBeanNullifier();
		dbn.setBean(jdbcDatabaseProcessContext());
		dbn.setNullTokens(Arrays.asList(Constants.NONE, Constants.NULL));
		dbn.setProperties(Arrays.asList("username", "password", "dbaUsername", "dbaPassword"));
		return dbn;
	}

	@Bean
	public DataSource jdbcDataSource() {
		jdbcNullifier().nullify();
		DatabaseProcessContext ctx = jdbcDatabaseProcessContext();
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(ctx.getDriver());
		dmds.setUrl(ctx.getDriver());
		dmds.setUsername(ctx.getUsername());
		dmds.setPassword(ctx.getPassword());
		return dmds;
	}

	@Bean
	public DataSource jdbcDbaDataSource() {
		jdbcNullifier().nullify();
		DatabaseProcessContext ctx = jdbcDatabaseProcessContext();
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(ctx.getDriver());
		dmds.setUrl(ctx.getDriver());
		dmds.setUsername(ctx.getDbaUsername());
		dmds.setPassword(ctx.getDbaPassword());
		return dmds;
	}

	@Bean
	public Executable jdbcShowConfigExecutable() {
		// Don't skip unless they have specifically set this property to true
		String skip = SpringUtils.getProperty(env, "jdbc.showconfig.skip", "false");

		ShowConfigExecutable sce = new ShowConfigExecutable();
		sce.setService(resetCommon.jdbcService());
		sce.setContext(jdbcDatabaseProcessContext());
		sce.setDataSource(jdbcDbaDataSource());
		sce.setSkip(new Boolean(skip));
		return sce;
	}
}
