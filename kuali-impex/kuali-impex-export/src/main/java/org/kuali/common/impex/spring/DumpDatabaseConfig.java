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

package org.kuali.common.impex.spring;

import org.kuali.common.impex.DumpDatabaseExecutable;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Configures tasks related to dumping data and schema information from a database to disk
 */
@Configuration
@Import({ JdbcDataSourceConfig.class, ExtractSchemaConfig.class, DumpSchemaConfig.class, DumpDataConfig.class })
public class DumpDatabaseConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	ExtractSchemaConfig extractSchemaConfig;

	@Autowired
	DumpSchemaConfig dumpSchemaConfig;

	@Autowired
	DumpDataConfig dumpDataConfig;

	@Bean
	public Executable dumpDatabaseExecutable() {

		boolean skip = SpringUtils.getBoolean(env, "impex.dump.skip", false);
		DumpDatabaseExecutable exec = new DumpDatabaseExecutable();
		exec.setSkip(skip);

		// Show the JDBC configuration
		exec.setShowConfigExecutable(dataSourceConfig.jdbcShowConfigExecutable());

		// Connect to the db and create model objects in memory that represent the schema
		exec.setExtractSchemaExecutable(extractSchemaConfig.extractSchemaExecutable());

		// Dump the schema model object from memory to disk as XML
		exec.setDumpSchemaExecutable(dumpSchemaConfig.dumpSchemaExecutable());

		// Connect to the db, extract data from the tables, and persist it to disk
		exec.setDumpDataExecutable(dumpDataConfig.dumpDataExecutable());

		// Return the configured executable
		return exec;
	}

}
