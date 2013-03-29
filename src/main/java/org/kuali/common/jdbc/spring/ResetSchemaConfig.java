package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public class ResetSchemaConfig {
	public static final String TYPE = "schema";
	public static final String SKIP_KEY = "jdbc.schema.skip";

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public Executable jdbcSchemaExecutable() {
		JdbcConfigContext jcc = new JdbcConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext context = JdbcConfigUtils.getConcurrentJdbcContext(jcc);
		context.setListener(new SummaryListener(false));
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(JdbcConfigUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

}
