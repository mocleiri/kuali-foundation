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

import org.kuali.common.impex.data.DataExportExecutable;
import org.kuali.common.impex.data.service.ExportDataContext;
import org.kuali.common.impex.data.service.ExportDataService;
import org.kuali.common.impex.data.service.impl.DefaultExportDataService;
import org.kuali.common.impex.util.ExportConstants;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataExportConfig {

    @Autowired
    Environment env;

    public static final String STATISTICS_LOCATION_KEY = "impex.export.data.statistics.location";
    public static final String DATA_THREADS_KEY = "impex.export.data.threads";
    public static final String WORKING_DIR_KEY = "impex.export.data.workingDir";
    public static final String ROW_INTERVAL_KEY = "impex.export.data.rowInterval";
    public static final String DATA_INTERVAL_KEY = "impex.export.data.dataInterval";
    public static final String TABLE_NAME_INCLUDE_KEY = "impex.export.data.include";
    public static final String TABLE_NAME_EXCLUDE_KEY = "impex.export.data.exclude";
    public static final String SERVICE_CLASS_NAME = "impex.export.data.service";

    /**
     * Property key for a boolean setting whether or not the executable should run
     */
    public static final String SKIP_EXECUTE_KEY = "impex.export.skip";

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Bean
    public ExportDataContext exportDataContext() {
        ExportDataContext result = new ExportDataContext();

        result.setTableStatisticsLocation(SpringUtils.getProperty(env, STATISTICS_LOCATION_KEY));

        result.setDataThreads(SpringUtils.getInteger(env, DATA_THREADS_KEY, ExportUtils.DEFAULT_DATA_THREADS));
        result.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, WORKING_DIR_KEY)));
        result.setRowCountInterval(SpringUtils.getInteger(env, ROW_INTERVAL_KEY, ExportUtils.DEFAULT_ROW_INTERVAL));
        result.setDataSizeInterval(SpringUtils.getInteger(env, DATA_INTERVAL_KEY, ExportUtils.DEFAULT_DATA_INTERVAL));

        result.setDataSource(dataSourceConfig.jdbcDataSource());
        result.setEncoding(dataSourceConfig.jdbcDatabaseProcessContext().getEncoding());

        List<String> tableIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLE_NAME_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
        List<String> tableExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLE_NAME_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

        StringFilter nameFilter = StringFilter.getInstance(tableIncludes, tableExcludes);

        result.setTableNameFilter(nameFilter);

        return result;
    }

    @Bean
    public ExportDataService exportDataService() {
        return SpringUtils.getInstance(env, SERVICE_CLASS_NAME, DefaultExportDataService.class);
    }

    @Bean
    public DataExportExecutable exportDataExecutable() {
        DataExportExecutable exec = new DataExportExecutable();
        exec.setSkip(SpringUtils.getBoolean(env, SKIP_EXECUTE_KEY, DataExportExecutable.DEFAULT_SKIP_EXECUTION));
        exec.setContext(exportDataContext());
        exec.setService(exportDataService());

        return exec;
    }

}
