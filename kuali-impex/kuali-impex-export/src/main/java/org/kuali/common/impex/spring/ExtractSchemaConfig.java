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

import org.kuali.common.impex.schema.MySqlSequenceFinder;
import org.kuali.common.impex.schema.MySqlViewFinder;
import org.kuali.common.impex.schema.OracleSequenceFinder;
import org.kuali.common.impex.schema.OracleViewFinder;
import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;
import org.kuali.common.impex.schema.service.ExtractSchemaContext;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
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
public class ExtractSchemaConfig {

	protected static final String DB_VENDOR_KEY = "db.vendor";

	public static final String THREAD_COUNT_KEY = "impex.extract.threads";

	protected static final Integer DEFAULT_THREAD_COUNT = 8;

	public static final String NAME_INCLUDES_KEY = "impex.extract.includes";

	public static final String SERVICE_KEY = "impex.extract.service";

	public static final String NAME_EXCLUDES_KEY = "impex.extract.excludes";

	protected static final String ORACLE_SEQUENCE_FINDER_KEY = "impex.extract.oracle.sequence.finder";
	protected static final String ORACLE_VIEW_FINDER_KEY = "impex.extract.oracle.view.finder";

	protected static final String MYSQL_SEQUENCE_FINDER_KEY = "impex.extract.mysql.sequence.finder";
	protected static final String MYSQL_VIEW_FINDER_KEY = "impex.extract.mysql.view.finder";

	// by default, include everything and exclude nothing
	protected static final String DEFAULT_NAME_INCLUDES = ".*";
	protected static final String DEFAULT_NAME_EXCLUDES = "";

	protected static final String SKIP_EXECUTION_KEY = "impex.extract.skip";

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public ExtractSchemaExecutable extractSchemaExecutable() {
		ExtractSchemaExecutable exec = new ExtractSchemaExecutable();
		exec.setContext(getSchemaExtractionContext());
		exec.setService(SpringUtils.getInstance(env, SERVICE_KEY, ExtractSchemaExecutable.DEFAULT_SERVICE.getClass()));
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_EXECUTION_KEY, ExtractSchemaExecutable.DEFAULT_SKIP));
		return exec;
	}

	/**
	 * Use the Environment and general Spring configuration to setup an extraction context
	 */
	protected ExtractSchemaContext getSchemaExtractionContext() {

		// This provides the configuration needed for connecting to the database
		DatabaseProcessContext dbContext = dataSourceConfig.jdbcDatabaseProcessContext();

		// This is the schema inside the database to extract
		String schemaName = dbContext.getUsername();

		// Number of threads to use
		int threadCount = SpringUtils.getInteger(env, THREAD_COUNT_KEY, DEFAULT_THREAD_COUNT);

		// DataSource for obtaining connections to the database
		DataSource dataSource = dataSourceConfig.jdbcDataSource();

		// The type of database we are connecting to
		String vendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

		// This is used to filter out tables/views/sequences
		StringFilter nameFilter = getNameFilter();

		// Get database specific finders for sequences and views
		SequenceFinder sequenceFinder = getSequenceFinderMap().get(vendor);
		ViewFinder viewFinder = getViewFinderMap().get(vendor);

		// Setup our context with pojo's aggregated from the Spring configuration
		ExtractSchemaContext context = new ExtractSchemaContext();
		context.setSchemaName(schemaName);
		context.setDataSource(dataSource);
		context.setNameFilter(nameFilter);
		context.setThreadCount(threadCount);
		context.setSequenceFinder(sequenceFinder);
		context.setViewFinder(viewFinder);
		return context;
	}

	protected StringFilter getNameFilter() {

		// Extract CSV values from the Environment
		String includesCSV = SpringUtils.getProperty(env, NAME_INCLUDES_KEY, DEFAULT_NAME_INCLUDES);
		String excludesCSV = SpringUtils.getProperty(env, NAME_EXCLUDES_KEY, DEFAULT_NAME_EXCLUDES);

		// Convert CSV to List
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

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
