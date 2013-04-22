package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlDbaAfterConfig extends AbstractSqlExecutionConfig {

	@Bean
	public Executable jdbcDbaExecutable() {
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.dba.after.skip", false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(SpringUtils.getProperty(env, "sql.dba.after.message"));
		ctx.setSkip(SpringUtils.getBoolean(env, "sql.dba.after.skip", false));
		ctx.setDataSource(dataSourceConfig.jdbcDbaDataSource());
		ctx.setSuppliers(Arrays.asList(getSqlSupplier()));
		ctx.setListener(new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE));
		return ctx;
	}

	protected SqlSupplier getSqlSupplier() {
		String sql = SpringUtils.getProperty(env, "sql.dba.after");
		ComplexStringSupplier css = new ComplexStringSupplier();
		css.setReader(commonConfig.jdbcSqlReader());
		css.setStrings(Arrays.asList(sql));
		return css;
	}

}
