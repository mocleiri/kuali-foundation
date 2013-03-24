package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, ResetDataSourceConfig.class })
public class ResetDbaConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	ResetDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcDbaExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.dba.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.dba.skip", "false");
		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(SpringUtils.getProperty(env, "sql.dba.message"));
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dbaConfig.jdbcDbaDataSource());
		ctx.setSuppliers(Arrays.asList(getSqlSupplier()));
		ctx.setListener(new LogSqlListener());
		return ctx;
	}

	protected SqlSupplier getSqlSupplier() {
		String validate = SpringUtils.getProperty(env, "sql.validate");
		String drop = SpringUtils.getProperty(env, "sql.drop");
		String create = SpringUtils.getProperty(env, "sql.create");

		ComplexStringSupplier css = new ComplexStringSupplier();
		css.setReader(commonConfig.jdbcSqlReader());
		css.setStrings(Arrays.asList(validate, drop, create));
		return css;
	}

}
