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

package org.kuali.common.impex.model.spring;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.compare.SchemaCompare;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public abstract class SchemaCompareConfig {

    protected static final String SCHEMA_1_DEFAULT_LABEL = "schema1";
    protected static final String SCHEMA_2_DEFAULT_LABEL = "schema2";

    protected static final String SCHEMA_1_LABEL_KEY = "impex.compare.schema1.label";
    protected static final String SCHEMA_2_LABEL_KEY = "impex.compare.schema2.label";

    @Autowired
    protected Environment env;

    @Bean
    public SchemaCompare schemaCompare() {
        Schema schema1 = schema1();
        schema1.setName(schema1Label());

        Schema schema2 = schema2();
        schema2.setName(schema2Label());

        return new SchemaCompare(schema1, schema2);
    }

    public abstract Schema schema1();

    public abstract Schema schema2();

    public String schema1Label() {
        return SpringUtils.getProperty(env, SCHEMA_1_LABEL_KEY, SCHEMA_1_DEFAULT_LABEL);
    }

    public String schema2Label() {
        return SpringUtils.getProperty(env, SCHEMA_2_LABEL_KEY, SCHEMA_2_DEFAULT_LABEL);
    }

}
