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

import org.kuali.common.impex.DatabaseExportExecutable;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configures tasks related to exporting data and schema information from a database to disk
 * 
 */
@Configuration
@Import({ JdbcDataSourceConfig.class, DataExportConfig.class, ModularSchemaExportConfig.class, SchemaExtractionConfig.class })
public class DatabaseExportConfig {

	@Autowired
	DataExportConfig dataExportConfig;

	@Autowired
	SchemaExtractionConfig extractSchemaConfig;

	@Autowired
	ModularSchemaExportConfig schemaExportConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public Executable exportDatabaseExecutable() {
		DatabaseExportExecutable executable = new DatabaseExportExecutable();

		// Show the JDBC configuration
		executable.setShowConfigExecutable(dataSourceConfig.jdbcShowConfigExecutable());

		// Connect to the db and create model objects in memory that represent the schema
		executable.setSchemaExtractionExecutable(extractSchemaConfig.schemaExtractionExecutable());

		// Persist the in memory model objects to disk as XML
		executable.setSchemaExportExecutables(schemaExportConfig.modularSchemaExportExecutables());

		// Connect to the db to extract data from the tables and persist it to disk
		executable.setDataExportExecutable(dataExportConfig.exportDataExecutable());

		// Return the configured executable
		return executable;
	}

}
