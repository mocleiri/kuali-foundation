package org.kuali.common.jdbc.spring;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetConstraintsConfig extends ResetBaseConfig {

	@Bean
	public Executable jdbcConstraintsExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.constraints.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.constraints.skip", "false");
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String message = SpringUtils.getProperty(env, "sql.constraints.concurrent.message");
		List<SqlSupplier> suppliers = commonConfig.getSqlSuppliers("sql.constraints.concurrent");
		DataSource dataSource = dataSourceConfig.jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		ctx.setListener(new SummaryListener(false));
		return ctx;
	}

}
