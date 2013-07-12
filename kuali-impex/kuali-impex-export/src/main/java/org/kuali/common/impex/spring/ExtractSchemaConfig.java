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

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;
import org.kuali.common.impex.schema.service.ExtractSchemaContext;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcDataSourceConfig.class, ExportServicesConfig.class })
public class ExtractSchemaConfig {

	private static final int DEFAULT_THREADS = 8;
	private static final String INCLUDES_KEY = "impex.extract.schema.includes";
	private static final String EXCLUDES_KEY = "impex.extract.schema.excludes";

	private static final String THREADS_KEY = "impex.extract.schema.threads";

	// By default, include everything, exclude nothing
	private static final String DEFAULT_INCLUDES = DumpConstants.DEFAULT_REGEX_INCLUDE;
	private static final String DEFAULT_EXCLUDES = DumpConstants.DEFAULT_REGEX_EXCLUDE;

	private static final String SKIP_KEY = "impex.extract.schema.skip";

	private static final String VIEW_FINDER_KEY = "impex.extract.schema.viewfinder";
	private static final String SEQUENCE_FINDER_KEY = "impex.extract.schema.sequencefinder";

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	ExportServicesConfig exportServicesConfig;

	@Bean
	public ExtractSchemaExecutable extractSchemaExecutable() {
		ExtractSchemaExecutable exec = new ExtractSchemaExecutable();
		exec.setContext(getSchemaExtractionContext());
		exec.setService(exportServicesConfig.exportExtractSchemaService());
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, ExtractSchemaExecutable.DEFAULT_SKIP));
		return exec;
	}

	/**
	 * Use the Environment and general Spring configuration to setup an extraction context
	 */
	protected ExtractSchemaContext getSchemaExtractionContext() {

		// This is the schema inside the database to extract
		String schemaName = dataSourceConfig.jdbcDatabaseProcessContext().getSchema();

		// Number of threads to use
		int threadCount = SpringUtils.getInteger(env, THREADS_KEY, DEFAULT_THREADS);

		// DataSource for obtaining connections to the database
		DataSource dataSource = dataSourceConfig.jdbcDataSource();

		// This is used to filter out tables/views/sequences/foreign keys
		StringFilter nameFilter = getNameFilter();

		// Get database specific finders for sequences and views
		SequenceFinder sequenceFinder = SpringUtils.getInstance(env, SEQUENCE_FINDER_KEY);
		ViewFinder viewFinder = SpringUtils.getInstance(env, VIEW_FINDER_KEY);

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

		// Convert CSV to List
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, INCLUDES_KEY, DEFAULT_INCLUDES);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, EXCLUDES_KEY, DEFAULT_EXCLUDES);

		// Setup the name filter
		return StringFilter.getInstance(includes, excludes);
	}

}
