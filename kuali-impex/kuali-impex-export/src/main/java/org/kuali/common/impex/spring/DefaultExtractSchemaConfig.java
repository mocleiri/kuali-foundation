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

	protected static final String THREAD_COUNT_KEY = "impex.export.schema.threads";

	protected static final Integer DEFAULT_THREAD_COUNT = 8;

	protected static final String NAME_INCLUDES_KEY = "impex.export.schema.includes";

	protected static final String NAME_EXCLUDES_KEY = "impex.export.schema.excludes";

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

		// DataSource for connecting to the database
		DataSource dataSource = dataSourceConfig.jdbcDataSource();

		// What type of database we are connecting to
		String dbVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

		// This is used to filter out tables/views/sequences
		StringFilter nameFilter = getNameFilter();

		SchemaExtractionContext context = new SchemaExtractionContext();
		context.setSchemaName(schemaName);
		context.setDataSource(dataSource);
		context.setNameFilter(nameFilter);
		context.setThreadCount(threadCount);

		context.setSequenceFinder(sequenceFinderMap().get(dbVendor));
		context.setViewFinder(viewFinderMap().get(dbVendor));

		return context;
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

	@Bean
	public Schema extractedSchema() {
		DefaultSchemaExtractionService service = new DefaultSchemaExtractionService();

		return service.getSchema(extractionContext());
	}

	@Bean
	public OracleSequenceFinder oracleSequenceFinder() {
		DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

		// schema name is the same as the user name
		return new OracleSequenceFinder(context.getUsername());
	}

	@Bean
	public MySqlSequenceFinder mySqlSequenceFinder() {
		return new MySqlSequenceFinder();
	}

	public OracleViewFinder oracleViewFinder() {
		DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

		OracleViewFinder finder = new OracleViewFinder();
		finder.setSchemaName(context.getUsername());

		return finder;
	}

	@Bean
	public MySqlViewFinder mySqlViewFinder() {
		DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

		MySqlViewFinder finder = new MySqlViewFinder();
		finder.setSchemaName(context.getUsername());

		return finder;
	}

	@Bean
	public Map<String, SequenceFinder> sequenceFinderMap() {
		Map<String, SequenceFinder> result = new HashMap<String, SequenceFinder>();

		result.put(OracleSequenceFinder.SUPPORTED_VENDOR, oracleSequenceFinder());
		result.put(MySqlSequenceFinder.SUPPORTED_VENDOR, mySqlSequenceFinder());

		return result;
	}

	@Bean
	public Map<String, ViewFinder> viewFinderMap() {
		Map<String, ViewFinder> result = new HashMap<String, ViewFinder>();

		result.put(OracleViewFinder.SUPPORTED_VENDOR, oracleViewFinder());
		result.put(MySqlViewFinder.SUPPORTED_VENDOR, mySqlViewFinder());

		return result;
	}

}
