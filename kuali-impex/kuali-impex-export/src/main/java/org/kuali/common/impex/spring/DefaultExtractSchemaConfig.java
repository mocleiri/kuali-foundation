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

import javax.sql.DataSource;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.MySqlSequenceFinder;
import org.kuali.common.impex.schema.MySqlViewFinder;
import org.kuali.common.impex.schema.OracleSequenceFinder;
import org.kuali.common.impex.schema.OracleViewFinder;
import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;
import org.kuali.common.impex.schema.service.impl.DefaultSchemaExtractionService;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcDataSourceConfig.class })
public class DefaultExtractSchemaConfig {

	protected static final String DB_VENDOR_KEY = "db.vendor";

	public static final String THREAD_COUNT_KEY = "impex.export.schema.threads";

	protected static final Integer DEFAULT_THREAD_COUNT = 8;

	public static final String NAME_INCLUDES_KEY = "impex.export.schema.includes";

	public static final String SCHEMA_EXTRACTION_SERVICE_KEY = "impex.export.schema.extraction.service";

	public static final String NAME_EXCLUDES_KEY = "impex.export.schema.excludes";

	protected static final String ORACLE_SEQUENCE_FINDER_KEY = "impex.export.oracle.sequence.finder";
	protected static final String ORACLE_VIEW_FINDER_KEY = "impex.export.oracle.view.finder";

	protected static final String MYSQL_SEQUENCE_FINDER_KEY = "impex.export.mysql.sequence.finder";
	protected static final String MYSQL_VIEW_FINDER_KEY = "impex.export.mysql.view.finder";

	// by default, include everything and exclude nothing
	protected static final String DEFAULT_NAME_INCLUDES = ".*";

	protected static final String DEFAULT_NAME_EXCLUDES = "";

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	/**
	 * Use the Environment and general Spring configuration to setup an extraction context
	 */
	@Bean
	public SchemaExtractionContext extractionContext() {

		// This provides the configuration needed for connecting to the database
		DatabaseProcessContext dbContext = dataSourceConfig.jdbcDatabaseProcessContext();

		// This is the schema inside the database to extract
		String schemaName = dbContext.getUsername();

		// Number of threads to use
		int threadCount = SpringUtils.getInteger(env, THREAD_COUNT_KEY, DEFAULT_THREAD_COUNT);

		// DataSource for obtaining connections to the database
		DataSource dataSource = dataSourceConfig.jdbcDataSource();

		// The type of database we are connecting to
		String dbVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

		// This is used to filter out tables/views/sequences
		StringFilter nameFilter = getNameFilter();

		// Get database specific finders for sequences and views
		SequenceFinder sequenceFinder = getSequenceFinderMap().get(dbVendor);
		ViewFinder viewFinder = getViewFinderMap().get(dbVendor);

		// Setup our context with the configuration built from the Spring configuration
		SchemaExtractionContext context = new SchemaExtractionContext();
		context.setSchemaName(schemaName);
		context.setDataSource(dataSource);
		context.setNameFilter(nameFilter);
		context.setThreadCount(threadCount);
		context.setSequenceFinder(sequenceFinder);
		context.setViewFinder(viewFinder);
		return context;
	}

	@Bean
	public Schema extractedSchema() {

		SchemaExtractionContext context = extractionContext();
		SchemaExtractionService service = SpringUtils.getInstance(env, SCHEMA_EXTRACTION_SERVICE_KEY, DefaultSchemaExtractionService.class);

		return service.getSchema(context);
	}

	protected StringFilter getNameFilter() {

		// Extract CSV values from the Environment
		String includesCsv = SpringUtils.getProperty(env, NAME_INCLUDES_KEY, DEFAULT_NAME_INCLUDES);
		String excludesCsv = SpringUtils.getProperty(env, NAME_EXCLUDES_KEY, DEFAULT_NAME_EXCLUDES);

		// Convert CSV to List
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCsv);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCsv);

		// Setup the name filter
		return StringFilter.getInstance(includes, excludes);
	}

	protected Map<String, SequenceFinder> getSequenceFinderMap() {

		SequenceFinder oracle = SpringUtils.getInstance(env, ORACLE_SEQUENCE_FINDER_KEY, OracleSequenceFinder.class);
		SequenceFinder mysql = SpringUtils.getInstance(env, MYSQL_SEQUENCE_FINDER_KEY, MySqlSequenceFinder.class);

		Map<String, SequenceFinder> result = new HashMap<String, SequenceFinder>();
		result.put(OracleSequenceFinder.SUPPORTED_VENDOR, oracle);
		result.put(MySqlSequenceFinder.SUPPORTED_VENDOR, mysql);
		return result;
	}

	protected Map<String, ViewFinder> getViewFinderMap() {

		ViewFinder oracle = SpringUtils.getInstance(env, ORACLE_VIEW_FINDER_KEY, OracleViewFinder.class);
		ViewFinder mysql = SpringUtils.getInstance(env, MYSQL_VIEW_FINDER_KEY, MySqlViewFinder.class);

		Map<String, ViewFinder> result = new HashMap<String, ViewFinder>();
		result.put(OracleViewFinder.SUPPORTED_VENDOR, oracle);
		result.put(MySqlViewFinder.SUPPORTED_VENDOR, mysql);
		return result;
	}

}
