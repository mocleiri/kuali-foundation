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

package org.kuali.common.impex.dump.spring;

import org.kuali.common.impex.data.DumpDataExecutable;
import org.kuali.common.impex.dump.exec.DumpDatabaseExecutable;
import org.kuali.common.impex.schema.execute.DumpSchemaExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.impex.spring.DumpDataConfig;
import org.kuali.common.impex.spring.DumpSchemaConfig;
import org.kuali.common.impex.spring.ExtractSchemaConfig;
import org.kuali.common.jdbc.show.ShowConfigExecutable;
import org.kuali.common.jdbc.show.spring.JdbcShowConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configures tasks related to dumping a database to disk
 */
@Configuration
@Import({ SpringServiceConfig.class, JdbcShowConfig.class, ExtractSchemaConfig.class, DumpSchemaConfig.class, DumpDataConfig.class })
// @Import({ ExtractSchemaConfig.class, DumpSchemaConfig.class, DumpDataConfig.class, SpringServiceConfig.class })
public class DumpDatabaseConfig {

	private static final String SKIP_KEY = "impex.dump.skip";

	@Autowired
	EnvironmentService env;

	@Autowired
	ShowConfigExecutable showConfigExec; // Show the JDBC connection details

	@Autowired
	ExtractSchemaExecutable extractSchemaExec; // Use JDBC calls to construct model objects representing the schema

	@Autowired
	DumpSchemaExecutable dumpSchemaExec; // Dump the schema model objects from memory to disk as XML

	@Autowired
	DumpDataExecutable dumpDataExec; // Extracts data from every non-empty table, dumps to disk as MPX files

	@Bean
	public Executable dumpDatabaseExecutable() {
		// Might be skipping execution all together
		boolean skip = env.getBoolean(SKIP_KEY, DumpDatabaseExecutable.DEFAULT_SKIP);

		// Return the configured executable
		return new DumpDatabaseExecutable(showConfigExec, extractSchemaExec, dumpSchemaExec, dumpDataExec, skip);
	}
}
