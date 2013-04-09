package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetConstraintsConfig extends ResetBaseConfig {

	public static final String TYPE = "constraints";
	public static final String SKIP_KEY = "jdbc." + TYPE + ".skip";

	@Bean
	public Executable jdbcConstraintsConcurrentExecutable() {
		ResetConfigContext rcc = new ResetConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext ctx = ResetConfigUtils.getConcurrentJdbcContext(rcc);
		ctx.setListener(ResetConfigUtils.getConstraintsListener(env));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(ctx);
		return exec;
	}

	@Bean
	public Executable jdbcConstraintsSequentialExecutable() {
		ResetConfigContext rcc = new ResetConfigContext(env, TYPE, SqlMode.SEQUENTIAL, commonConfig, dataSourceConfig);
		JdbcContext ctx = ResetConfigUtils.getSequentialJdbcContext(rcc);
		ctx.setListener(ResetConfigUtils.getConstraintsListener(env));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(ctx);
		return exec;
	}

}
