package org.kuali.common.jdbc.sql.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcContextsExecutableConfig {

	@Autowired
	JdbcContextsConfig config;

	@Autowired
	JdbcService service;

	@Bean(initMethod = "execute")
	public Executable executeSql() {
		List<JdbcContext> contexts = config.jdbcContexts();
		List<Executable> execs = new ArrayList<Executable>();
		for (JdbcContext context : contexts) {
			execs.add(new JdbcExecutable(service, context));
		}
		return new ExecutablesExecutable(execs);
	}

}
