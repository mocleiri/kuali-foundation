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

import org.kuali.common.jalc.model.DataType;
import org.kuali.common.jalc.schema.DataTypeMapping;
import org.kuali.common.jalc.schema.DataTypeMappingProvider;
import org.kuali.common.jalc.schema.impl.DefaultDataTypeMappingProvider;
import org.kuali.common.jalc.schema.impl.NoOpProvider;
import org.kuali.common.jalc.schema.impl.mysql.MySqlSchemaProducer;
import org.kuali.common.jalc.schema.impl.oracle.OracleSchemaProducer;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataTypeMappingProviderConfig {

    // TODO KSENROLL-6764 Should be able to retrieve this as an enum from a kuali-jdbc level config
    protected static final String DB_VENDOR_KEY = "db.vendor";

    @Autowired
    Environment env;

    @Bean
    public DataTypeMappingProvider defaultMappingProvider() {

        String dbVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

        if (dbVendor.equalsIgnoreCase(OracleSchemaProducer.SUPPORTED_VENDOR)) {
            DefaultDataTypeMappingProvider mappingProvider = new DefaultDataTypeMappingProvider();

            mappingProvider.getDataTypeMatches().put(DataType.DATE, overrideOracleDateColumnSize());
            mappingProvider.getDataTypeMatches().put(DataType.TIMESTAMP, overrideOracleTimestampColumnSize());
            mappingProvider.getDataTypeMatches().put(DataType.CLOB, overrideOracleClobColumnSize());
            mappingProvider.getDataTypeMatches().put(DataType.BLOB, overrideOracleBlobColumnSize());

            return mappingProvider;
        }

        if (dbVendor.equalsIgnoreCase(MySqlSchemaProducer.SUPPORTED_VENDOR)) {
            return new NoOpProvider();
        }

        throw new UnsupportedOperationException("Could not map db vendor '" + dbVendor + "' to a known SchemaSqlProducer implementation");

    }

    @Bean
    public DataTypeMapping overrideOracleDateColumnSize() {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(DataType.DATE);
        mapping.setTypeSize(null);

        return mapping;
    }

    @Bean
    public DataTypeMapping overrideOracleBlobColumnSize() {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(DataType.BLOB);
        mapping.setTypeSize(null);

        return mapping;
    }

    @Bean
    public DataTypeMapping overrideOracleClobColumnSize() {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(DataType.CLOB);
        mapping.setTypeSize(null);

        return mapping;
    }

    @Bean
    public DataTypeMapping overrideOracleTimestampColumnSize() {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(DataType.TIMESTAMP);
        mapping.setTypeSize(null);

        return mapping;
    }

}
