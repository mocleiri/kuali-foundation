package org.kuali.common.jdbc.spring;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.SummaryListener;
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
public class ResetOtherConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	ResetDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcOtherConcurrentExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.other.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getConcurrentJdbcContext());
		return exec;
	}

	@Bean
	public Executable jdbcOtherSequentialExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.other.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getSequentialJdbcContext());
		return exec;
	}

	protected JdbcContext getSequentialJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.other.skip", "false");
		String message = SpringUtils.getProperty(env, "sql.other.sequential.message");
		List<SqlSupplier> suppliers = commonConfig.getSqlSuppliers("sql.other.sequential");
		DataSource dataSource = dbaConfig.jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		ctx.setListener(new SummaryListener(true));
		return ctx;
	}

	protected JdbcContext getConcurrentJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.other.skip", "false");
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String message = SpringUtils.getProperty(env, "sql.other.concurrent.message");
		List<SqlSupplier> suppliers = commonConfig.getSqlSuppliers("sql.other.concurrent");
		DataSource dataSource = dbaConfig.jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		ctx.setListener(new SummaryListener(true));
		return ctx;
	}

}
