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

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.DefaultDumpSchemaService;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.schema.execute.DumpSchemaExecutableOld;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SchemaExportConfig {

	/**
	 * The ExportSchemaService implementation to use
	 */
	protected static final String EXPORT_SCHEMA_SERVICE_KEY = "impex.export.schema.service";

	/**
	 * Property key for the location of the xml file for any schema elements that are not specified for other files
	 */
	protected static final String SCHEMA_LOCATION_KEY = "impex.export.schema.location";

	/**
	 * Property key for the location of the xml file for tables
	 */
	protected static final String TABLES_LOCATION_KEY = "impex.export.schema.tables.location";

	/**
	 * Property key for the location of the xml file for views
	 */
	protected static final String VIEWS_LOCATION_KEY = "impex.export.schema.views.location";

	/**
	 * Property key for the location of the xml file for sequences
	 */
	protected static final String SEQUENCES_LOCATION_KEY = "impex.export.schema.sequences.location";

	/**
	 * Property key for the location of the xml file for foreign keys
	 */
	protected static final String FOREIGNKEY_LOCATION_KEY = "impex.export.schema.foreignkeys.location";

	/**
	 * Property key for a boolean setting whether or not the executable should run
	 */
	protected static final String SKIP_EXECUTION_KEY = "impex.export.skip";

	@Autowired
	Environment env;

	/**
	 * Build a map of resource locations to schema element types
	 * 
	 * This allows for defining a whole schema in one file, or breaking the definition up into separate files (one for tables, one for views, etc)
	 * 
	 * @return a Map of location strings to schema element types
	 */
	@Bean
	public Map<String, Class<? extends NamedElement>> schemaLocations() {

		// This is the default location where all schema info will be written.
		// Individual types of JDBC schema info can be written to separate locations if desired by providing a location for each type of info
		String schemaLocation = SpringUtils.getProperty(env, SCHEMA_LOCATION_KEY);

		// Allocate some storage for mapping db schemas to output locations
		Map<String, Class<? extends NamedElement>> result = new HashMap<String, Class<? extends NamedElement>>();

		// The location to write table information
		String tableLocation = SpringUtils.getProperty(env, TABLES_LOCATION_KEY, schemaLocation);
        result.put(tableLocation, Table.class);

		// The location to write view information
		String viewLocation = SpringUtils.getProperty(env, VIEWS_LOCATION_KEY, schemaLocation);
        result.put(viewLocation, View.class);

		// The location to write sequence information
		String sequenceLocation = SpringUtils.getProperty(env, SEQUENCES_LOCATION_KEY, schemaLocation);
        result.put(sequenceLocation, Sequence.class);

		// The location to write foreign key information
		String foreignKeyLocation = SpringUtils.getProperty(env, FOREIGNKEY_LOCATION_KEY, schemaLocation);
        result.put(foreignKeyLocation, ForeignKey.class);

		return result;
	}

    @Bean
    public DumpSchemaService exportService() {
        return SpringUtils.getInstance(env, EXPORT_SCHEMA_SERVICE_KEY, DefaultDumpSchemaService.class);
    }

    @Bean
    public Boolean executionSkip() {
        return SpringUtils.getBoolean(env, SKIP_EXECUTION_KEY, DumpSchemaExecutableOld.DEFAULT_SKIP_EXECUTION);
    }

	@Bean
	public DumpSchemaExecutableOld exportSchemaExecutable() {

		DumpSchemaExecutableOld exec = new DumpSchemaExecutableOld();

		exec.setExportService(exportService());
        exec.setSkip(executionSkip());

		return exec;
	}

}
