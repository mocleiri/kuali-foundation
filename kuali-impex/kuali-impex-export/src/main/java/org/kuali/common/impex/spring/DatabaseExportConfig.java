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

import org.kuali.common.impex.DatabaseExportExecutable;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configures tasks related to exporting data and schema information from a database to disk
 *
 */
@Configuration
@Import({DataExportConfig.class, ModularSchemaExportConfig.class, SchemaExtractionConfig.class})
public class DatabaseExportConfig {

    @Autowired
    DataExportConfig dataExportConfig;

    @Autowired
    ModularSchemaExportConfig schemaExportConfig;

    @Autowired
    SchemaExtractionConfig extractSchemaConfig;

    @Bean
    public Executable exportDatabaseExecutable() {
        DatabaseExportExecutable executable = new DatabaseExportExecutable();

        executable.setSchemaExtractionExecutable(extractSchemaConfig.schemaExtractionExecutable());
        executable.setDataExportExecutable(dataExportConfig.exportDataExecutable());
        executable.setSchemaExecutables(schemaExportConfig.modularSchemaExportExecutables());

        return executable;
    }

}
