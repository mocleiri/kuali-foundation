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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.data.DefaultExportDataService;
import org.kuali.common.impex.data.ExportDataContext;
import org.kuali.common.impex.data.ExportDataService;
import org.kuali.common.impex.data.ExportTableContext;
import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.util.ExportConstants;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ModularDataExportConfig {

    @Autowired
    Environment env;

    @Autowired
    ModelProvider modelProvider;

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    public static final String PROPERTY_PREFIXES_KEY = "impex.export.data.modular.prefixes";

    public static final String STATISTICS_LOCATION_KEY = "impex.export.data.statistics.location";

    public static final String NAME_INCLUDE_KEY = "data.include";

    public static final String NAME_EXCLUDE_KEY = "data.exclude";

    public static final String OUTPUT_LOCATION_KEY = "data.output";

    public static final String SKIP_EXECUTION_KEY = "data.skip";

    public static final String DATA_THREADS_KEY = "data.threads";

    public static final String ROW_INTERVAL_KEY = "data.rowInterval";

    public static final String DATA_INTERVAL_KEY = "data.dataInterval";

    @Bean(initMethod = "execute")
    public Executable modularDataExportExecutable() {
        List<String> prefixes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, PROPERTY_PREFIXES_KEY));

        List<Executable> executables = new ArrayList<Executable>(prefixes.size());

        for (String prefix : prefixes) {
            ExportDataContext dataContext = new ExportDataContext();

            dataContext.setDataSource(dataSourceConfig.jdbcDataSource());
            dataContext.setEncoding(dataSourceConfig.jdbcDatabaseProcessContext().getEncoding());

            dataContext.setDataThreads(SpringUtils.getInteger(env, prefix + DATA_THREADS_KEY, ExportUtils.DEFAULT_DATA_THREADS));
            dataContext.setWorkingDir(LocationUtils.getFileQuietly(SpringUtils.getProperty(env, prefix + OUTPUT_LOCATION_KEY)));
            dataContext.setRowCountInterval(SpringUtils.getInteger(env, prefix + ROW_INTERVAL_KEY, ExportUtils.DEFAULT_ROW_INTERVAL));
            dataContext.setDataSizeInterval(SpringUtils.getInteger(env, prefix + DATA_INTERVAL_KEY, ExportUtils.DEFAULT_DATA_INTERVAL));

            List<String> includes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, prefix + NAME_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
            List<String> excludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, prefix + NAME_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

            StringFilter filter = StringFilter.getInstance(includes, excludes);

            Collection<Table> includedTables = ExportUtils.getIncludedElements(filter, modelProvider.getTables());

            List<ExportTableContext> tableContexts = new ArrayList<ExportTableContext>(includedTables.size());

            // create table contexts
            for (Table t : includedTables) {

                ExportTableContext context = new ExportTableContext(t);

                ExportUtils.populateTableStatistics(tableStatistics(), t, context);

                tableContexts.add(context);
            }

            dataContext.setTableContexts(tableContexts);

            boolean skip = SpringUtils.getBoolean(env, prefix + SKIP_EXECUTION_KEY, ModularDataExportExecutable.DEFAULT_EXECUTION_SKIP);

            ModularDataExportExecutable dataExportExecutable = new ModularDataExportExecutable(dataContext, dataService());
            dataExportExecutable.setSkip(skip);

            executables.add(dataExportExecutable);
        }

        ExecutablesExecutable ex = new ExecutablesExecutable();
        ex.setExecutables(executables);
        return ex;
    }

    @Bean
    public ExportDataService dataService() {
        return new DefaultExportDataService();
    }

    @Bean
    public Properties tableStatistics() {
        String statsLocation = SpringUtils.getProperty(env, STATISTICS_LOCATION_KEY);
        Properties tableStatistics = null;
        if(LocationUtils.exists(statsLocation)) {
            tableStatistics = PropertyUtils.load(statsLocation);
        }

        return tableStatistics;
    }

}
