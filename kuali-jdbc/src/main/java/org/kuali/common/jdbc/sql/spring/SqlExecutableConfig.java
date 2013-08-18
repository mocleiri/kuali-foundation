package org.kuali.common.jdbc.sql.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.model.context.SqlExecutionContext;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlExecutableConfig {

	@Autowired
	SqlExecutionContextsConfig config;

	@Autowired
	JdbcService service;

	@Bean
	@Execute
	public Executable executeSql() {
		List<SqlExecutionContext> contexts = config.sqlExecutionContexts();
		List<JdbcExecutable> execs = new ArrayList<JdbcExecutable>();
		for (SqlExecutionContext context : contexts) {
			for (JdbcContext jc : context.getContexts()) {
				JdbcExecutable exec = new JdbcExecutable(service, jc, context.isSkip());
				execs.add(exec);
			}
		}
		return new ExecutablesExecutable(execs);
	}

}
