package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.SummaryListener;
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
@Import({ ResetCommon.class, ResetDataSource.class })
public class ResetSchema {

	@Autowired
	Environment env;

	@Autowired
	ResetCommon resetCommon;

	@Autowired
	ResetDataSource resetDataSource;

	@Bean
	public Executable jdbcSchemaExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.schema.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(resetCommon.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.schema.skip", "false");
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String message = SpringUtils.getProperty(env, "sql.schema.concurrent.message");

		JdbcContext ctx = new JdbcContext();
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(resetDataSource.jdbcDbaDataSource());
		ctx.setSuppliers(Arrays.asList(getSqlSupplier()));
		ctx.setListener(new SummaryListener(false));
		return ctx;
	}

	protected SqlSupplier getSqlSupplier() {
		String validate = SpringUtils.getProperty(env, "sql.validate");
		String drop = SpringUtils.getProperty(env, "sql.drop");
		String create = SpringUtils.getProperty(env, "sql.create");

		ComplexStringSupplier css = new ComplexStringSupplier();
		css.setReader(resetCommon.jdbcSqlReader());
		css.setStrings(Arrays.asList(validate, drop, create));
		return css;
	}

}
