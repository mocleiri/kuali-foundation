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

import org.kuali.common.impex.data.DumpDataExecutable;
import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.impex.util.DumpUtils;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DumpDataConfig {

	// Required (no default value)
	public static final String DIR_KEY = "impex.dump.data.dir";
	public static final String STATS_LOCATION_KEY = "impex.dump.data.stats.location";

	// These are usually customized but do have default values
	public static final String INCLUDES_KEY = "impex.dump.data.includes";
	public static final String EXCLUDES_KEY = "impex.dump.data.excludes";
	public static final String ENCODING_KEY = "impex.dump.data.encoding";

	// Defaults for these are usually ok
	public static final String THREADS_KEY = "impex.dump.data.threads";
	public static final String ROW_INTERVAL_KEY = "impex.dump.data.rowInterval";
	public static final String DATA_INTERVAL_KEY = "impex.dump.data.dataInterval";
	public static final String SERVICE_KEY = "impex.dump.data.service";
	public static final String SKIP_KEY = "impex.dump.data.skip";

	@Autowired
	Environment env;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public DumpDataExecutable dumpDataExecutable() {

		// Extract some context from the Environment
		DumpDataContext context = getDumpDataContext();

		// Setup an executable for exporting the data
		DumpDataExecutable exec = new DumpDataExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, DumpDataExecutable.DEFAULT_SKIP_EXECUTION));
		exec.setService(SpringUtils.getInstance(env, SERVICE_KEY, DumpDataExecutable.DEFAULT_SERVICE.getClass()));
		exec.setContext(context);
		return exec;
	}

	protected DumpDataContext getDumpDataContext() {
		DumpDataContext context = new DumpDataContext();
		context.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, DIR_KEY)));
		context.setTableStatisticsLocation(SpringUtils.getProperty(env, STATS_LOCATION_KEY));
		context.setDataThreads(SpringUtils.getInteger(env, THREADS_KEY, DumpUtils.DEFAULT_DATA_THREADS));
		context.setRowCountInterval(SpringUtils.getInteger(env, ROW_INTERVAL_KEY, DumpUtils.DEFAULT_ROW_INTERVAL));
		context.setDataSizeInterval(SpringUtils.getBytesInteger(env, DATA_INTERVAL_KEY, DumpUtils.DEFAULT_DATA_INTERVAL));
		context.setDataSource(dataSourceConfig.jdbcDataSource());
		context.setEncoding(dataSourceConfig.jdbcDatabaseProcessContext().getEncoding());
		context.setTableNameFilter(getTableNameFilter());
		return context;
	}

	protected StringFilter getTableNameFilter() {
		List<String> tableIncludes = SpringUtils.getListFromCSV(env, INCLUDES_KEY, DumpConstants.DEFAULT_INCLUDE);
		List<String> tableExcludes = SpringUtils.getListFromCSV(env, EXCLUDES_KEY, DumpConstants.DEFAULT_EXCLUDE);
		return StringFilter.getInstance(tableIncludes, tableExcludes);
	}

}
