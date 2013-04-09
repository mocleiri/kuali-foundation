package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Default database reset controller class. It displays the JDBC configuration, then executes a series of SQL statements in order [dba->schema->data->constraints->other].
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class, ResetDbaConfig.class, ResetSchemaConfig.class, ResetConstraintsConfig.class, ResetOtherConfig.class })
public class ResetController {

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	ResetDbaConfig dbaConfig;

	@Autowired
	ResetSchemaConfig schemaConfig;

	@Autowired
	ResetDataConfig dataConfig;

	@Autowired
	ResetConstraintsConfig constraintsConfig;

	@Autowired
	ResetOtherConfig otherConfig;

	@Bean(initMethod = "execute")
	public Executable jdbcResetExecutable() {
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(dataSourceConfig.jdbcShowConfigExecutable());
		executables.add(dbaConfig.jdbcDbaExecutable());
		executables.add(schemaConfig.jdbcSchemaExecutable());
		executables.add(dataConfig.jdbcDataConcurrentExecutable());
		executables.add(dataConfig.jdbcDataSequentialExecutable());
		executables.add(constraintsConfig.jdbcConstraintsConcurrentExecutable());
		executables.add(otherConfig.jdbcOtherConcurrentExecutable());
		executables.add(otherConfig.jdbcOtherSequentialExecutable());

		ExecutablesExecutable exec = new ExecutablesExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.reset.skip", false));
		exec.setTimed(SpringUtils.getBoolean(env, "jdbc.reset.timed", true));
		exec.setExecutables(executables);
		return exec;
	}
}
