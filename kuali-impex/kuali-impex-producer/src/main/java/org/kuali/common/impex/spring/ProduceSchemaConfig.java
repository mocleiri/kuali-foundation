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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.ProduceSchemaExecutable;
import org.kuali.common.impex.schema.SchemaSqlProducer;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ XmlSchemaConfig.class, SchemaSqlProducerConfig.class })
public class ProduceSchemaConfig {

    @Autowired
    Environment env;

    @Autowired
    Schema schema;

    @Autowired
    SchemaSqlProducer schemaProducer;

    /**
     * This property key points to the user defined folder to output sql files A property with the key is required to build the executable
     */
    protected final static String DUMP_FOLDER_KEY = "impex.schemadump.folder";

    /**
     * This property key points to the user defined prefix for dumped file names
     */
    protected final static String FILENAME_PREFIX_KEY = "impex.schemadump.prefix";

    /**
     * This property key maps to a boolean value of whether or not to skip execution
     */
    protected final static String SKIP_EXECUTION_KEY = "impex.schemadump.skip";

    protected final static String DEFAULT_FILENAME_PREFIX = "dump-";

    protected final static String TABLE_FILE = "tables.sql";
    protected final static String FOREIGN_KEY_FILE = "foreignKeys.sql";
    protected final static String SEQUENCE_FILE = "sequences.sql";
    protected final static String VIEW_FILE = "views.sql";

    @Bean(initMethod = "execute")
    public ProduceSchemaExecutable executable() {
        ProduceSchemaExecutable exec = new ProduceSchemaExecutable();
        exec.setFileNamesToSqls(fileNamesToSqls());
        exec.setSkip(skipExecution());

        return exec;
    }

    @Bean
    public boolean skipExecution() {
        return SpringUtils.getBoolean(env, SKIP_EXECUTION_KEY, ProduceSchemaExecutable.DEFAULT_SKIP_EXECUTION);
    }

    @Bean
    public Map<String, List<String>> fileNamesToSqls() {

        Map<String, List<String>> fileNamesToSqls = new HashMap<String, List<String>>();

        fileNamesToSqls.put(getFileName(TABLE_FILE), schemaProducer.getTablesSql(schema.getTables()));
        fileNamesToSqls.put(getFileName(FOREIGN_KEY_FILE), schemaProducer.getForeignKeySql(schema.getForeignKeys()));
        fileNamesToSqls.put(getFileName(SEQUENCE_FILE), schemaProducer.getSequencesSql(schema.getSequences()));
        fileNamesToSqls.put(getFileName(VIEW_FILE), schemaProducer.getViewsSql(schema.getViews()));

        return fileNamesToSqls;
    }

    protected String getFileName(String fileNameSuffix) {
        StringBuilder sb = new StringBuilder();

        String dumpFolder = SpringUtils.getProperty(env, DUMP_FOLDER_KEY);
        String fileNamePrefix = SpringUtils.getProperty(env, FILENAME_PREFIX_KEY, DEFAULT_FILENAME_PREFIX);

        sb.append(dumpFolder).append(fileNamePrefix).append(fileNameSuffix);

        return sb.toString();
    }
}
