/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.context.SqlExecutionContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * @deprecated
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class, SqlDbaBeforeConfig.class, SqlDbaAfterConfig.class })
@Deprecated
public abstract class AbstractSqlController {

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	SqlDbaBeforeConfig dbaBeforeConfig;

	@Autowired
	SqlDbaAfterConfig dbaAfterConfig;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Bean
	public Executable sqlExecutable() {
		return getSqlExecutable();
	}

	protected Executable getSqlExecutable() {
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(dataSourceConfig.jdbcShowDbaConfigExecutable());
		executables.add(dbaBeforeConfig.getDbaPhaseExecutable());
		List<SqlExecutionContext> contexts = SqlConfigUtils.getSqlExecutionContexts(env);

		for (SqlExecutionContext context : contexts) {
			SqlConfigContext scc = new SqlConfigContext(env, context, commonConfig, dataSourceConfig);
			Executable executable = SqlConfigUtils.getJdbcExecutable(scc);
			executables.add(executable);
		}
		executables.add(dbaAfterConfig.getDbaPhaseExecutable());

		ExecutablesExecutable exec = new ExecutablesExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.reset.skip", false));
		exec.setTimed(SpringUtils.getBoolean(env, "jdbc.reset.timed", true));
		exec.setExecutables(executables);
		return exec;
	}

}
