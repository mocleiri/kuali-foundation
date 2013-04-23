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

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Default database reset controller class. It displays the JDBC configuration, then executes a series of SQL statements in order [dba->schema->data->constraints->other].
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class, SqlDbaBeforeConfig.class, ResetSchemaConfig.class, ResetConstraintsConfig.class, ResetOtherConfig.class,
		SqlDbaAfterConfig.class })
public abstract class AbstractResetController {

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	SqlDbaBeforeConfig dbaConfig;

	@Autowired
	ResetSchemaConfig schemaConfig;

	@Autowired
	ResetDataConfig dataConfig;

	@Autowired
	ResetConstraintsConfig constraintsConfig;

	@Autowired
	ResetOtherConfig otherConfig;

	@Autowired
	SqlDbaAfterConfig dbaCleanupConfig;

	protected Executable getResetExecutable() {
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(dataSourceConfig.jdbcShowConfigExecutable());
		executables.add(dbaConfig.jdbcDbaExecutable());
		executables.add(schemaConfig.jdbcSchemaExecutable());
		executables.add(dataConfig.jdbcDataConcurrentExecutable());
		executables.add(dataConfig.jdbcDataSequentialExecutable());
		executables.add(constraintsConfig.jdbcConstraintsConcurrentExecutable());
		executables.add(constraintsConfig.jdbcConstraintsSequentialExecutable());
		executables.add(otherConfig.jdbcOtherConcurrentExecutable());
		executables.add(otherConfig.jdbcOtherSequentialExecutable());
		executables.add(dbaCleanupConfig.jdbcDbaCleanupExecutable());

		ExecutablesExecutable exec = new ExecutablesExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.reset.skip", false));
		exec.setTimed(SpringUtils.getBoolean(env, "jdbc.reset.timed", true));
		exec.setExecutables(executables);
		return exec;
	}

}
