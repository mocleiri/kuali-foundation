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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configures tasks related to dumping data and schema information from a database to disk
 */
@Configuration
@Import({ JdbcDataSourceConfig.class, ExtractSchemaConfig.class, DumpSchemaConfig.class, DumpDataConfig.class })
public class DumpDatabaseConfig {

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
		DumpDatabaseExecutable executable = new DumpDatabaseExecutable();

		// Show the JDBC configuration
		executable.setShowConfigExecutable(dataSourceConfig.jdbcShowConfigExecutable());

		// Connect to the db and create model objects in memory that represent the schema
		executable.setExtractSchemaExecutable(extractSchemaConfig.extractSchemaExecutable());

		// Dump the schema model object from memory to disk as XML
		executable.setDumpSchemaExecutable(dumpSchemaConfig.dumpSchemaExecutable());

		// Connect to the db, extract data from the tables, and persist it to disk
		executable.setDumpDataExecutable(dumpDataConfig.dumpDataExecutable());

		// Return the configured executable
		return executable;
	}

}
