package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetOtherConfig extends AbstractSqlExecutionConfig {

	public static final String TYPE = "other";
	public static final String SKIP_KEY = "jdbc.other.skip";

	@Bean
	public Executable jdbcOtherConcurrentExecutable() {

		ResetConfigContext rcc = new ResetConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext context = ResetConfigUtils.getSequentialJdbcContext(rcc);
		context.setListener(ResetConfigUtils.getOtherListener(env));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

	@Bean
	public Executable jdbcOtherSequentialExecutable() {

		ResetConfigContext rcc = new ResetConfigContext(env, TYPE, SqlMode.SEQUENTIAL, commonConfig, dataSourceConfig);
		JdbcContext context = ResetConfigUtils.getSequentialJdbcContext(rcc);
		context.setListener(ResetConfigUtils.getOtherListener(env));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

}
