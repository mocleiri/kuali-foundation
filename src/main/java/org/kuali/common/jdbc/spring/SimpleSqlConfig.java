/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.GroupedSqlConfig;
import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.JdbcContextUtils;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * This class configures a simple JdbcContext, with one set of scripts from a fixed property key in the environment
 * 
 * @author andrewlubbers
 * 
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public class SimpleSqlConfig implements GroupedSqlConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	private static final String LOCATIONS_KEY = "kjdbc.sql.locations";

	private static final String SQLMODE_KEY = "kjdbc.sql.mode";

	public static final String SKIP_KEY = "kjdbc.sql.skip";

	@Bean
	public Executable jdbcSimpleSqlExecutable() {

		JdbcContext context = JdbcContextUtils.buildJdbcContextFromGroupedSql(this);

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(context);
		return exec;
	}

	@Override
	public String getGroupKey() {
		return LOCATIONS_KEY;
	}

	@Override
	public SqlMode getSqlMode() {
		String sqlMode = SpringUtils.getProperty(env, SQLMODE_KEY);

		return SqlMode.valueOf(sqlMode.toUpperCase());
	}

	@Override
	public JdbcDataSourceConfig getDataSourceConfig() {
		return dataSourceConfig;
	}

	@Override
	public Environment getEnvironment() {
		return env;
	}

	@Override
	public JdbcCommonConfig getJdbcCommonConfig() {
		return commonConfig;
	}

	@Override
	public SqlListener getSqlListener() {
		return JdbcContextUtils.buildSummaryListener(env);
	}
}
