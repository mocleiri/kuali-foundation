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
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ JdbcCommonConfig.class, ResetDataSourceConfig.class, ResetDbaConfig.class, ResetSchemaConfig.class, ResetConstraintsConfig.class, ResetOtherConfig.class })
public class ResetController {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	ResetDataSourceConfig dataSourceConfig;

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
		String skip = SpringUtils.getProperty(env, "jdbc.reset.skip", "false");
		String timed = SpringUtils.getProperty(env, "jdbc.reset.timed", "true");

		List<Executable> executables = new ArrayList<Executable>();
		executables.add(dataSourceConfig.jdbcShowConfigExecutable());
		executables.add(dbaConfig.jdbcDbaExecutable());
		executables.add(schemaConfig.jdbcSchemaExecutable());
		executables.add(dataConfig.jdbcDataConcurrentExecutable());
		executables.add(dataConfig.jdbcDataSequentialExecutable());
		executables.add(constraintsConfig.jdbcConstraintsExecutable());
		executables.add(otherConfig.jdbcOtherConcurrentExecutable());
		executables.add(otherConfig.jdbcOtherSequentialExecutable());

		ExecutablesExecutable exec = new ExecutablesExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setTimed(new Boolean(timed));
		exec.setExecutables(executables);
		return exec;
	}
}
