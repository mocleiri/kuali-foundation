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

package org.kuali.common.jalc.model.spring;

import org.kuali.common.jalc.model.ModelProvider;
import org.kuali.common.jalc.model.Schema;
import org.kuali.common.jalc.model.compare.SchemaCompare;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class SchemaCompareConfig {

    protected static final String SCHEMA_1_DEFAULT_LABEL = "schema1";
    protected static final String SCHEMA_2_DEFAULT_LABEL = "schema2";

    protected static final String SCHEMA_1_LABEL_KEY = "jalc.compare.schema1.label";
    protected static final String SCHEMA_2_LABEL_KEY = "jalc.compare.schema2.label";

    @Bean
    public SchemaCompare schemaCompare() {
        Schema schema1 = new Schema();
        // set name to a default
        schema1.setName(schema1Label());

        Schema schema2 = new Schema();
        schema2.setName(schema2Label());

        populateSchema(modelProvider1(), schema1);
        populateSchema(modelProvider2(), schema2);

        return new SchemaCompare(schema1, schema2);
    }

    protected void populateSchema(ModelProvider modelProvider, Schema schema) {
        schema.getTables().addAll(modelProvider.getTables());
        schema.getViews().addAll(modelProvider.getViews());
        schema.getSequences().addAll(modelProvider.getSequences());
        schema.getForeignKeys().addAll(modelProvider.getForeignKeys());
    }

    public abstract ModelProvider modelProvider1();

    public abstract ModelProvider modelProvider2();

    public abstract String schema1Label();

    public abstract String schema2Label();

}
