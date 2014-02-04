package org.kuali.common.jdbc.reset;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.show.spring.JdbcShowConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcServiceConfig.class, JdbcShowConfig.class })
public class DefaultJdbcResetConfig implements JdbcResetConfig {

	private static final String TIMED_KEY = "jdbc.reset.timed";
	private static final String SKIP_KEY = "jdbc.reset.skip";

	@Autowired
	JdbcService service;

	@Autowired
	JdbcShowConfig show;

	@Autowired
	EnvironmentService env;

	@Autowired
	JdbcContextsConfig contextsConfig;

	@Override
	@Bean
	public Executable jdbcResetExecutable() {
		List<JdbcContext> contexts = contextsConfig.jdbcContexts();
		boolean skip = env.getBoolean(SKIP_KEY, false);
		boolean timed = env.getBoolean(TIMED_KEY, true);
		List<Executable> execs = new ArrayList<Executable>();
		execs.add(show.showDbaConfigExecutable());
		for (JdbcContext context : contexts) {
			execs.add(new JdbcExecutable(service, context));
		}
		return new ExecutablesExecutable(execs, skip, timed);
	}

}
