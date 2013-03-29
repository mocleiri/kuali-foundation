package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public class ResetSchemaConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public Executable jdbcSchemaExecutable() {
		String type = "schema";
		String skip = SpringUtils.getProperty(env, "jdbc." + type + ".skip", "false");

		JdbcConfigContext jcc = new JdbcConfigContext(env, type, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext context = JdbcConfigUtils.getConcurrentJdbcContext(jcc);
		context.setListener(new SummaryListener(false));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

}
