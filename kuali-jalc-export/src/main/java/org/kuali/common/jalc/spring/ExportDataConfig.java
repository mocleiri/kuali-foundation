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

package org.kuali.common.jalc.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jalc.data.DefaultExportDataService;
import org.kuali.common.jalc.data.ExportDataContext;
import org.kuali.common.jalc.data.ExportDataService;
import org.kuali.common.jalc.data.ExportTableContext;
import org.kuali.common.jalc.model.ModelProvider;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jalc.util.ExportConstants;
import org.kuali.common.jalc.util.ExportUtils;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ LiquibaseModelProviderConfig.class})
public class ExportDataConfig {

    @Autowired
    Environment env;

    protected static final String PROJECT_PREFIX = "jalc.";

    protected static final String STATISTICS_LOCATION_KEY = PROJECT_PREFIX + "export.data.statistics.location";
    protected static final String DATA_THREADS_KEY = PROJECT_PREFIX + "export.data.threads";
    protected static final String WORKING_DIR_KEY = PROJECT_PREFIX + "export.data.workingDir";
    protected static final String ROW_INTERVAL_KEY = PROJECT_PREFIX + "export.data.rowInterval";
    protected static final String DATA_INTERVAL_KEY = PROJECT_PREFIX + "export.data.dataInterval";
    protected static final String TABLE_NAME_INCLUDE_KEY = PROJECT_PREFIX + "export.data.include";
    protected static final String TABLE_NAME_EXCLUDE_KEY = PROJECT_PREFIX + "export.data.exclude";

    /**
     * Property key for a boolean setting whether or not the executable should run
     */
    protected static final String EXECUTE_ENABLED_KEY = PROJECT_PREFIX + "export.execute";

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Autowired
    ModelProvider modelProvider;

    @Bean
    public ExportDataContext exportDataContext() {
        ExportDataContext result = new ExportDataContext();

        String statsLocation = SpringUtils.getProperty(env, STATISTICS_LOCATION_KEY);
        Properties tableStatistics = null;
        if(LocationUtils.exists(statsLocation)) {
            tableStatistics = PropertyUtils.load(statsLocation);
        }

        result.setDataThreads(SpringUtils.getInteger(env, DATA_THREADS_KEY, ExportUtils.DEFAULT_DATA_THREADS));
        result.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, WORKING_DIR_KEY)));
        result.setRowCountInterval(SpringUtils.getInteger(env, ROW_INTERVAL_KEY, ExportUtils.DEFAULT_ROW_INTERVAL));
        result.setDataSizeInterval(SpringUtils.getInteger(env, DATA_INTERVAL_KEY, ExportUtils.DEFAULT_DATA_INTERVAL));

        result.setDataSource(dataSourceConfig.jdbcDataSource());
        result.setEncoding(dataSourceConfig.jdbcDatabaseProcessContext().getEncoding());

        List<ExportTableContext> tableContexts = new ArrayList<ExportTableContext>();

        Collection<Table> includedTables = ExportUtils.getIncludedElements(tableNameFilter(), modelProvider.getTables());

        // create table contexts
        for (Table t : includedTables) {

            ExportTableContext context = new ExportTableContext(t);

            ExportUtils.populateTableStatistics(tableStatistics, t, context);

            tableContexts.add(context);
        }

        result.setTableContexts(tableContexts);

        return result;
    }

    @Bean
    private StringFilter tableNameFilter() {
        List<String> tableIncludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLE_NAME_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
        List<String> tableExcludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, TABLE_NAME_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

        return StringFilter.getInstance(tableIncludes, tableExcludes);
    }

    @Bean
    public ExportDataService exportDataService() {
        return new DefaultExportDataService();
    }

    @Bean(initMethod = "execute")
    public ExportDataExecutable exportDataExecutable() {
        return new ExportDataExecutable(executableEnabled());
    }

    @Bean
    private Boolean executableEnabled() {
        return SpringUtils.getBoolean(env, EXECUTE_ENABLED_KEY, ExportDataExecutable.DEFAULT_EXECUTE_ENABLED);
    }
}
