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

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jalc.data.DefaultExportDataService;
import org.kuali.common.jalc.data.ExportDataContext;
import org.kuali.common.jalc.data.ExportDataService;
import org.kuali.common.jalc.data.ExportTableContext;
import org.kuali.common.jalc.model.ModelProvider;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcDataSourceConfig.class, LiquibaseModelProviderConfig.class})
public class ExportDataConfig {

    @Autowired
    Environment env;

    protected static final String PROJECT_PREFIX = "jalc.";

    protected static final String STATISTICS_LOCATION_KEY = PROJECT_PREFIX + "export.data.statistics.location";
    protected static final String DATA_THREADS_KEY = PROJECT_PREFIX + "export.data.threads";
    protected static final String WORKING_DIR_KEY = PROJECT_PREFIX + "export.data.workingDir";
    protected static final String ROW_INTERVAL_KEY = PROJECT_PREFIX + "export.data.rowInterval";
    protected static final String DATA_INTERVAL_KEY = PROJECT_PREFIX + "export.data.dataInterval";

    /**
     * Property key for a boolean setting whether or not the executable should run
     */
    protected static final String EXECUTE_ENABLED_KEY = PROJECT_PREFIX + "export.execute";

    protected static final String SIZE_PROPERTY_SUFFIX = ".size";
    protected static final String ROWS_PROPERTY_SUFFIX = ".rows";

    protected static final Integer DEFAULT_ROW_INTERVAL = 50;
    protected static final Integer DEFAULT_DATA_INTERVAL = 50 * 1024;

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Autowired
    ModelProvider modelProvider;

    @Bean
    public ExportDataContext exportDataContext() {
        ExportDataContext result = new ExportDataContext();

        result.setStatisticsLocation(SpringUtils.getProperty(env, STATISTICS_LOCATION_KEY));
        Properties tableStatistics = null;
        if(LocationUtils.exists(result.getStatisticsLocation())) {
            tableStatistics = PropertyUtils.load(result.getStatisticsLocation());
        }

        result.setDataThreads(Integer.parseInt(SpringUtils.getProperty(env, DATA_THREADS_KEY)));
        result.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, WORKING_DIR_KEY)));
        result.setRowCountInterval(SpringUtils.getInteger(env, ROW_INTERVAL_KEY, DEFAULT_ROW_INTERVAL));
        result.setDataSizeInterval(SpringUtils.getInteger(env, DATA_INTERVAL_KEY, DEFAULT_DATA_INTERVAL));

        result.setDataSource(dataSourceConfig.jdbcDataSource());

        // create table contexts
        for (Table t : modelProvider.getTables()) {
            ExportTableContext context = new ExportTableContext(t);

            if(tableStatistics != null) {
                String sizeVal = tableStatistics.getProperty(t.getName() + SIZE_PROPERTY_SUFFIX);
                String rowCountVal = tableStatistics.getProperty(t.getName() + ROWS_PROPERTY_SUFFIX);

                if (StringUtils.isNotBlank(sizeVal)) {
                    context.setSize(Long.parseLong(sizeVal));
                }
                if (StringUtils.isNotBlank(rowCountVal)) {
                    context.setRowCount(Long.parseLong(rowCountVal));
                }
            }
        }

        return result;
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
