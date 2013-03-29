package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ JdbcCommonConfig.class, ResetDataSourceConfig.class })
public class ResetSchemaConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	ResetDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcSchemaExecutable() {
		String fragment = "schema";

		String skip = SpringUtils.getProperty(env, "jdbc." + fragment + ".skip", "false");

		JdbcContext context = ConfigUtils.getConcurrentJdbcContext(env, fragment, commonConfig, dbaConfig);

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

}
