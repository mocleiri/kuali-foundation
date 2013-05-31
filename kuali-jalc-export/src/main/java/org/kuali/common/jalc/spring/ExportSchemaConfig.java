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

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jalc.model.ModelProvider;
import org.kuali.common.jalc.model.Schema;
import org.kuali.common.jalc.schema.DefaultExportSchemaService;
import org.kuali.common.jalc.schema.ExportSchemaService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(LiquibaseModelProviderConfig.class)
public class ExportSchemaConfig {

    protected static final String PROJECT_PREFIX = "jalc.";

    /**
     * Property key for the location of the xml file for tables
     */
    protected static final String TABLES_LOCATION_KEY = PROJECT_PREFIX + "export.schema.tables";

    /**
     * Property key for the location of the xml file for views
     */
    protected static final String VIEWS_LOCATION_KEY = PROJECT_PREFIX + "export.schema.views";

    /**
     * Property key for the location of the xml file for sequences
     */
    protected static final String SEQUENCES_LOCATION_KEY = PROJECT_PREFIX + "export.schema.sequences";

    /**
     * Property key for the location of the xml file for foreign keys
     */
    protected static final String FOREIGNKEY_LOCATION_KEY = PROJECT_PREFIX + "export.schema.foreignkeys";


    /**
     * Property key for a boolean setting whether or not the executable should run
     */
    protected static final String EXECUTE_ENABLED_KEY = PROJECT_PREFIX + "export.execute";


    @Autowired
    Environment env;

    @Autowired
    ModelProvider modelProvider;

    /**
     * Build a map of resource locations to schema instances
     *
     * This allows for defining a whole schema in one file, or breaking the definition up
     * into separate files (one for tables, one for views, etc)
     *
     * @return a Map of location strings to Schema instances, populated with schema objects
     *         provided by the ModelProvider
     */
    @Bean
    public Map<String, Schema> schemaLocations() {


        Map<String, Schema> result = new HashMap<String, Schema>();

        Schema schema;

        String tableLocation = SpringUtils.getProperty(env, TABLES_LOCATION_KEY);
        schema = quietlyGetSchema(tableLocation, result);
        schema.getTables().addAll(modelProvider.getTables());

        String viewLocation = SpringUtils.getProperty(env, VIEWS_LOCATION_KEY);
        schema = quietlyGetSchema(viewLocation, result);
        schema.getViews().addAll(modelProvider.getViews());

        String sequenceLocation = SpringUtils.getProperty(env, SEQUENCES_LOCATION_KEY);
        schema = quietlyGetSchema(sequenceLocation, result);
        schema.getSequences().addAll(modelProvider.getSequences());

        String foreignKeyLocation = SpringUtils.getProperty(env, FOREIGNKEY_LOCATION_KEY);
        schema = quietlyGetSchema(foreignKeyLocation, result);
        schema.getForeignKeys().addAll(modelProvider.getForeignKeys());

        return result;
    }

    protected Schema quietlyGetSchema(String location, Map<String, Schema> schemaMap) {
        if (!schemaMap.containsKey(location)) {
            schemaMap.put(location, new Schema());
        }

        return schemaMap.get(location);
    }

    @Bean
    public ExportSchemaService persistService() {
        return new DefaultExportSchemaService();
    }

    @Bean(initMethod = "execute")
    public ExportSchemaExecutable exportSchemaExecutable() {
        return new ExportSchemaExecutable(executableEnabled());
    }

    @Bean
    public Boolean executableEnabled() {
        return SpringUtils.getBoolean(env, EXECUTE_ENABLED_KEY, ExportSchemaExecutable.DEFAULT_EXECUTE_ENABLED);
    }
}
