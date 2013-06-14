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
import java.util.List;
import java.util.Map;

import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.DefaultExportSchemaService;
import org.kuali.common.impex.schema.ExportSchemaExecutable;
import org.kuali.common.impex.schema.ExportSchemaService;
import org.kuali.common.impex.util.ExportConstants;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(LiquibaseModelProviderConfig.class)
public class ExportSchemaConfig {

	protected static final String PROJECT_PREFIX = "impex.";

	/**
	 * The ExprotSchemaService implementation to use
	 */
	protected static final String EXPORT_SCHEMA_SERVICE = PROJECT_PREFIX + "export.schema.service";

	/**
	 * Property key for the location of the xml file for tables
	 */
	protected static final String TABLES_LOCATION_KEY = PROJECT_PREFIX + "export.schema.tables.location";

	/**
	 * Property key for the location of the xml file for views
	 */
	protected static final String VIEWS_LOCATION_KEY = PROJECT_PREFIX + "export.schema.views.location";

	/**
	 * Property key for the location of the xml file for sequences
	 */
	protected static final String SEQUENCES_LOCATION_KEY = PROJECT_PREFIX + "export.schema.sequences.location";

	/**
	 * Property key for the location of the xml file for foreign keys
	 */
	protected static final String FOREIGNKEY_LOCATION_KEY = PROJECT_PREFIX + "export.schema.foreignkeys.location";

	/**
	 * Property key for the regular expression to match table names for export
	 */
	protected static final String TABLES_INCLUDE_KEY = PROJECT_PREFIX + "export.schema.tables.include";

	/**
	 * Property key for the regular expression to match view names for export
	 */
	protected static final String VIEWS_INCLUDE_KEY = PROJECT_PREFIX + "export.schema.views.include";

	/**
	 * Property key for the regular expression to match sequence names for export
	 */
	protected static final String SEQUENCES_INCLUDE_KEY = PROJECT_PREFIX + "export.schema.sequences.include";

	/**
	 * Property key for the regular expression to match foreign key names for export
	 */
	protected static final String FOREIGNKEYS_INCLUDE_KEY = PROJECT_PREFIX + "export.schema.foreignkeys.include";

	/**
	 * Property key for the regular expression to exclude table names for export
	 */
	protected static final String TABLES_EXCLUDE_KEY = PROJECT_PREFIX + "export.schema.tables.exclude";

	/**
	 * Property key for the regular expression to exclude view names for export
	 */
	protected static final String VIEWS_EXCLUDE_KEY = PROJECT_PREFIX + "export.schema.views.exclude";

	/**
	 * Property key for the regular expression to exclude sequence names for export
	 */
	protected static final String SEQUENCES_EXCLUDE_KEY = PROJECT_PREFIX + "export.schema.sequences.exclude";

	/**
	 * Property key for the regular expression to exclude foreign key names for export
	 */
	protected static final String FOREIGNKEYS_EXCLUDE_KEY = PROJECT_PREFIX + "export.schema.foreignkeys.exclude";

	/**
	 * Property key for a boolean setting whether or not the executable should run
	 */
	protected static final String SKIP_EXECUTION_KEY = PROJECT_PREFIX + "export.skip";

	@Autowired
	Environment env;

	@Autowired
	ModelProvider modelProvider;

	/**
	 * Build a map of resource locations to schema instances
	 * 
	 * This allows for defining a whole schema in one file, or breaking the definition up into separate files (one for tables, one for views, etc)
	 * 
	 * @return a Map of location strings to Schema instances, populated with schema objects provided by the ModelProvider
	 */
	@Bean
	public Map<String, Schema> schemaLocations() {
		Map<String, Schema> result = new HashMap<String, Schema>();

		Schema schema;

		String tableLocation = SpringUtils.getProperty(env, TABLES_LOCATION_KEY);
		schema = quietlyGetSchema(tableLocation, result);
		schema.getTables().addAll(ExportUtils.getIncludedElements(tableNameFilter(), modelProvider.getTables()));

		String viewLocation = SpringUtils.getProperty(env, VIEWS_LOCATION_KEY);
		schema = quietlyGetSchema(viewLocation, result);
		schema.getViews().addAll(ExportUtils.getIncludedElements(viewNameFilter(), modelProvider.getViews()));

		String sequenceLocation = SpringUtils.getProperty(env, SEQUENCES_LOCATION_KEY);
		schema = quietlyGetSchema(sequenceLocation, result);
		schema.getSequences().addAll(ExportUtils.getIncludedElements(sequenceNameFilter(), modelProvider.getSequences()));

		String foreignKeyLocation = SpringUtils.getProperty(env, FOREIGNKEY_LOCATION_KEY);
		schema = quietlyGetSchema(foreignKeyLocation, result);
		schema.getForeignKeys().addAll(ExportUtils.getIncludedElements(foreignKeyNameFilter(), modelProvider.getForeignKeys()));

		return result;
	}

	@Bean
	public StringFilter tableNameFilter() {
		List<String> tableIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLES_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
		List<String> tableExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLES_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

		return StringFilter.getInstance(tableIncludes, tableExcludes);
	}

	@Bean
	public StringFilter viewNameFilter() {
		List<String> viewIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, VIEWS_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
		List<String> viewExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, VIEWS_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

		return StringFilter.getInstance(viewIncludes, viewExcludes);
	}

	@Bean
	public StringFilter sequenceNameFilter() {
		List<String> tableIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, SEQUENCES_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
		List<String> tableExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, SEQUENCES_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

		return StringFilter.getInstance(tableIncludes, tableExcludes);
	}

	@Bean
	public StringFilter foreignKeyNameFilter() {
		List<String> foreignKeyIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, FOREIGNKEYS_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
		List<String> foreignKeyExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, FOREIGNKEYS_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

		return StringFilter.getInstance(foreignKeyIncludes, foreignKeyExcludes);
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

		ExportSchemaService service = SpringUtils.getInstance(env, "", DefaultExportSchemaService.class);

		ExportSchemaExecutable exec = new ExportSchemaExecutable(skip);
		exec.setSchemaLocations(schemaLocations());
		exec.setExportService(service);

		return exec;
	}

}
