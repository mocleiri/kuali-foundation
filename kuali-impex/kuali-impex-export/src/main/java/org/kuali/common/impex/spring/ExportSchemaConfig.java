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

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.DefaultExportSchemaService;
import org.kuali.common.impex.schema.ExportSchemaExecutable;
import org.kuali.common.impex.schema.ExportSchemaService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ExportSchemaConfig {

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

	@Autowired
	Schema schema;

	/**
	 * Build a map of resource locations to schema instances
	 * 
	 * This allows for defining a whole schema in one file, or breaking the definition up into separate files (one for tables, one for views, etc)
	 * 
	 * @return a Map of location strings to Schema instances, populated with schema objects provided by the ModelProvider
	 */
	@Bean
	public Map<String, Schema> schemaLocations() {

		// This is the default location where all schema info will be written.
		// Individual types of JDBC schema info can be written to separate locations if desired by providing a location for each type of info
		String schemaLocation = SpringUtils.getProperty(env, SCHEMA_LOCATION_KEY);

		// Allocate some storage for mapping db schemas to output locations
		Map<String, Schema> result = new HashMap<String, Schema>();

		Schema s;

		// The location to write table information to
		String tableLocation = SpringUtils.getProperty(env, TABLES_LOCATION_KEY, schemaLocation);
		s = quietlyGetSchema(tableLocation, result);
		s.getTables().addAll(this.schema.getTables());

		// The location to write view information to
		String viewLocation = SpringUtils.getProperty(env, VIEWS_LOCATION_KEY, schemaLocation);
		s = quietlyGetSchema(viewLocation, result);
		s.getViews().addAll(this.schema.getViews());

		// The location to write sequence information to
		String sequenceLocation = SpringUtils.getProperty(env, SEQUENCES_LOCATION_KEY, schemaLocation);
		s = quietlyGetSchema(sequenceLocation, result);
		s.getSequences().addAll(this.schema.getSequences());

		// The location to write foreign key information to
		String foreignKeyLocation = SpringUtils.getProperty(env, FOREIGNKEY_LOCATION_KEY, schemaLocation);
		s = quietlyGetSchema(foreignKeyLocation, result);
		s.getForeignKeys().addAll(this.schema.getForeignKeys());

		return result;
	}

	protected Schema quietlyGetSchema(String location, Map<String, Schema> schemaMap) {
		if (!schemaMap.containsKey(location)) {
			schemaMap.put(location, new Schema());
		}
		return schemaMap.get(location);
	}

	@Bean(initMethod = "execute")
	public ExportSchemaExecutable exportSchemaExecutable() {

		boolean skip = SpringUtils.getBoolean(env, SKIP_EXECUTION_KEY, ExportSchemaExecutable.DEFAULT_SKIP_EXECUTION);
		ExportSchemaService service = SpringUtils.getInstance(env, EXPORT_SCHEMA_SERVICE_KEY, DefaultExportSchemaService.class);

		ExportSchemaExecutable exec = new ExportSchemaExecutable(skip);
		exec.setSchemaLocations(schemaLocations());
		exec.setExportService(service);
		return exec;
	}

}
