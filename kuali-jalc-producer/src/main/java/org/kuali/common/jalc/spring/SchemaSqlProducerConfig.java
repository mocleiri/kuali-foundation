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

import org.kuali.common.jalc.schema.SchemaSqlProducer;
import org.kuali.common.jalc.schema.impl.mysql.MySqlSchemaProducer;
import org.kuali.common.jalc.schema.impl.oracle.OracleSchemaProducer;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({DataTypeMappingProviderConfig.class})
public class SchemaSqlProducerConfig {

    // TODO KSENROLL-6764 Should be able to retrieve this as an enum from a kuali-jdbc level config
    protected static final String DB_VENDOR_KEY = "db.vendor";

    @Autowired
    Environment env;

    @Autowired
    DataTypeMappingProviderConfig mappingProviderConfig;

    @Bean
    public SchemaSqlProducer schemaSqlProducer() {

        String dbVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

        if (dbVendor.equalsIgnoreCase(OracleSchemaProducer.SUPPORTED_VENDOR)) {
            OracleSchemaProducer schemaProducer = new OracleSchemaProducer();
            schemaProducer.getTableSqlProducer().setMappingProvider(mappingProviderConfig.defaultMappingProvider());
            return schemaProducer;
        }

        if (dbVendor.equalsIgnoreCase(MySqlSchemaProducer.SUPPORTED_VENDOR)) {
            MySqlSchemaProducer schemaProducer = new MySqlSchemaProducer();
            schemaProducer.getTableSqlProducer().setMappingProvider(mappingProviderConfig.defaultMappingProvider());
            return schemaProducer;
        }

        throw new UnsupportedOperationException("Could not map db vendor '" + dbVendor + "' to a known SchemaSqlProducer implementation");
    }

}
