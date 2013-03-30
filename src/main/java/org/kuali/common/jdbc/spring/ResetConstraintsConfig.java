package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetConstraintsConfig extends ResetBaseConfig {

	public static final String TYPE = "constraints";
	public static final String SKIP_KEY = "jdbc.constraints.skip";

	@Bean
	public Executable jdbcConstraintsExecutable() {
		JdbcConfigContext jcc = new JdbcConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext ctx = JdbcConfigUtils.getConcurrentJdbcContext(jcc);
		ctx.setListener(new SummaryListener(false));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(ctx);
		return exec;
	}

}
