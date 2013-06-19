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

import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.schema.DataTypeMapping;
import org.kuali.common.impex.schema.DataTypeMappingProvider;
import org.kuali.common.impex.schema.impl.DefaultDataTypeMappingProvider;
import org.kuali.common.impex.schema.impl.mysql.MySqlSchemaProducer;
import org.kuali.common.impex.schema.impl.oracle.OracleSchemaProducer;
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
            DefaultDataTypeMappingProvider mappingProvider = new DefaultDataTypeMappingProvider();

            mappingProvider.getDataTypeMatches().put(DataType.DATE, overrideMySqlDateColumnSize());
            mappingProvider.getDataTypeMatches().put(DataType.TIMESTAMP, overrideMySqlTimestampColumnSize());
            mappingProvider.getDataTypeMatches().put(DataType.CLOB, overrideMySqlClobColumnSize());

            return mappingProvider;
        }

        throw new UnsupportedOperationException("Could not map db vendor '" + dbVendor + "' to a known SchemaSqlProducer implementation");

    }

    @Bean
    public DataTypeMapping overrideMySqlDateColumnSize() {
        return nullTypeSizeMapping(DataType.DATE);
    }

    @Bean
    public DataTypeMapping overrideMySqlTimestampColumnSize() {
        return nullTypeSizeMapping(DataType.TIMESTAMP);
    }

    @Bean
    public DataTypeMapping overrideMySqlClobColumnSize() {
        return nullTypeSizeMapping(DataType.CLOB);
    }

    @Bean
    public DataTypeMapping overrideOracleDateColumnSize() {
        return nullTypeSizeMapping(DataType.DATE);
    }

    @Bean
    public DataTypeMapping overrideOracleBlobColumnSize() {
        return nullTypeSizeMapping(DataType.BLOB);
    }

    @Bean
    public DataTypeMapping overrideOracleClobColumnSize() {
        return nullTypeSizeMapping(DataType.CLOB);
    }

    @Bean
    public DataTypeMapping overrideOracleTimestampColumnSize() {
        return nullTypeSizeMapping(DataType.TIMESTAMP);
    }

    protected DataTypeMapping nullTypeSizeMapping(DataType type) {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(type);
        mapping.setTypeSize(null);

        return mapping;
    }

}
