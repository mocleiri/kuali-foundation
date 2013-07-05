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
import org.kuali.common.impex.util.ExportUtils;
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

	public static final String WORKING_DIR_KEY = "impex.dump.dir";
	public static final String TABLE_NAME_INCLUDE_KEY = "impex.dump.includes";
	public static final String TABLE_NAME_EXCLUDE_KEY = "impex.dump.excludes";

	public static final String STATISTICS_LOCATION_KEY = "impex.dump.data.statistics.location";
	public static final String DATA_THREADS_KEY = "impex.dump.data.threads";
	public static final String ROW_INTERVAL_KEY = "impex.dump.data.rowInterval";
	public static final String DATA_INTERVAL_KEY = "impex.dump.data.dataInterval";
	public static final String SERVICE_CLASS_NAME = "impex.dump.data.service";

	/**
	 * Property key that determines if the executable will run
	 */
	public static final String SKIP_EXECUTE_KEY = "impex.dump.data.skip";

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
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_EXECUTE_KEY, DumpDataExecutable.DEFAULT_SKIP_EXECUTION));
		exec.setService(SpringUtils.getInstance(env, SERVICE_CLASS_NAME, DumpDataExecutable.DEFAULT_SERVICE.getClass()));
		exec.setContext(context);
		return exec;
	}

	protected DumpDataContext getDumpDataContext() {
		DumpDataContext context = new DumpDataContext();
		context.setTableStatisticsLocation(SpringUtils.getProperty(env, STATISTICS_LOCATION_KEY));
		context.setDataThreads(SpringUtils.getInteger(env, DATA_THREADS_KEY, ExportUtils.DEFAULT_DATA_THREADS));
		context.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, WORKING_DIR_KEY)));
		context.setRowCountInterval(SpringUtils.getInteger(env, ROW_INTERVAL_KEY, ExportUtils.DEFAULT_ROW_INTERVAL));
		context.setDataSizeInterval(SpringUtils.getBytesInteger(env, DATA_INTERVAL_KEY, ExportUtils.DEFAULT_DATA_INTERVAL));
		context.setDataSource(dataSourceConfig.jdbcDataSource());
		context.setEncoding(dataSourceConfig.jdbcDatabaseProcessContext().getEncoding());
		context.setTableNameFilter(getTableNameFilter());
		return context;
	}

	protected StringFilter getTableNameFilter() {
		List<String> tableIncludes = SpringUtils.getListFromCSV(env, TABLE_NAME_INCLUDE_KEY, DumpConstants.DEFAULT_INCLUDE);
		List<String> tableExcludes = SpringUtils.getListFromCSV(env, TABLE_NAME_EXCLUDE_KEY, DumpConstants.DEFAULT_EXCLUDE);
		return StringFilter.getInstance(tableIncludes, tableExcludes);
	}

}
