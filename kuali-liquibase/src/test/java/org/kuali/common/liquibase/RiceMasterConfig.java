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
package org.kuali.common.liquibase;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.sql.spring.DbaContextConfig;
import org.kuali.common.liquibase.spring.LiquibaseServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcServiceConfig.class, DbaContextConfig.class, DataSourceConfig.class, LiquibaseServiceConfig.class })
public class RiceMasterConfig {

	@Autowired
	DbaContextConfig dbaContextConfig;

	@Autowired
	JdbcServiceConfig jdbcServiceConfig;

	@Autowired
	LiquibaseServiceConfig liquibaseServiceConfig;

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Bean(initMethod = "execute")
	public Executable executable() {
		JdbcContext before = dbaContextConfig.dbaBeforeContext();
		JdbcContext after = dbaContextConfig.dbaAfterContext();
		JdbcExecutable je1 = new JdbcExecutable(jdbcServiceConfig.jdbcService(), before);
		JdbcExecutable je2 = new JdbcExecutable(jdbcServiceConfig.jdbcService(), after);
		Executable liquibase = liquibaseExecutable();
		return new ExecutablesExecutable(je1, liquibase, je2);
	}

	@Bean
	public Executable liquibaseExecutable() {
		LiquibaseService service = liquibaseServiceConfig.liquibaseService();
		DataSource dataSource = dataSourceConfig.dataSource();
		String changeLog = "org/kuali/rice/rice-liquibase/initial-db/change-log.xml";
		List<String> contexts = Arrays.asList("master");
		LiquibaseContext context = new LiquibaseContext.Builder(dataSource, changeLog).contexts(contexts).build();
		return new LiquibaseUpdateExecutable.Builder(service, context).build();
	}
}
