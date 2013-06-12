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
import java.util.List;

import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.DefaultExportSchemaService;
import org.kuali.common.impex.util.ExportConstants;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * This class supports exporting a schema with multiple output modules
 *
 * @author andrewlubbers
 */
@Configuration
public class ModularSchemaExportConfig {

    public static final String PROPERTY_PREFIXES_KEY = "impex.export.schema.modular.prefixes";

    public static final String NAME_INCLUDE_KEY = "schema.include";

    public static final String NAME_EXCLUDE_KEY = "schema.exclude";

    public static final String OUTPUT_LOCATION_KEY = "schema.output";

    public static final String EXECUTION_SKIP_KEY = "schema.skip";

    public static final String SEPARATE_FOREIGN_KEYS_KEY = "schema.foreignKeys.separate";

    public static final String FOREIGN_KEY_OUTPUT_LOCATION_KEY = "schema.foreignKeys.output";

    public static final boolean DEFAULT_SEPARATE_FOREIGN_KEYS = true;

    @Autowired
    Environment env;

    @Autowired
    ModelProvider modelProvider;

    @Bean(initMethod = "execute")
    public Executable modularSchemaExportExecutable() {

        List<String> prefixes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, PROPERTY_PREFIXES_KEY));

        List<Executable> executables = new ArrayList<Executable>(prefixes.size());

        for (String prefix : prefixes) {
            List<String> includes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, prefix + NAME_INCLUDE_KEY, ExportConstants.DEFAULT_INCLUDE));
            List<String> excludes = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, prefix + NAME_EXCLUDE_KEY, ExportConstants.DEFAULT_EXCLUDE));

            StringFilter filter = StringFilter.getInstance(includes, excludes);

            String outpuLocation = SpringUtils.getProperty(env, prefix + OUTPUT_LOCATION_KEY);

            Schema schema = new Schema();
            schema.getTables().addAll(ExportUtils.getIncludedElements(filter, modelProvider.getTables()));
            schema.getForeignKeys().addAll(ExportUtils.getIncludedElements(filter, modelProvider.getForeignKeys()));
            schema.getSequences().addAll(ExportUtils.getIncludedElements(filter, modelProvider.getSequences()));
            schema.getViews().addAll(ExportUtils.getIncludedElements(filter, modelProvider.getViews()));

            boolean skip = SpringUtils.getBoolean(env, prefix + EXECUTION_SKIP_KEY, ModularSchemaExportExecutable.DEFAULT_EXECUTION_SKIP);
            // if this property is set to true for any module, then foreign key schema will be created in a separate file
            boolean separateForeignKeys = SpringUtils.getBoolean(env, prefix + SEPARATE_FOREIGN_KEYS_KEY, DEFAULT_SEPARATE_FOREIGN_KEYS);

            ModularSchemaExportExecutable mexec = new ModularSchemaExportExecutable();
            mexec.setOutputLocation(outpuLocation);
            mexec.setSchema(schema);
            mexec.setExportService(new DefaultExportSchemaService());
            mexec.setSkip(skip);
            mexec.setSeparateForeignKeys(separateForeignKeys);
            if(separateForeignKeys) {
                String fkOutpuLocation = SpringUtils.getProperty(env, prefix + FOREIGN_KEY_OUTPUT_LOCATION_KEY);
                mexec.setForeignKeyOutputLocation(fkOutpuLocation);
            }

            executables.add(mexec);
        }

        ExecutablesExecutable ex = new ExecutablesExecutable();
        ex.setExecutables(executables);
        return ex;
    }

}
