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
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataTypeMappingProviderConfig {

    protected static final String DATATYPE_SIZE_OVERRIDES_KEY = "producer.sql.schema.datatype.size.overrides";

    protected static final String DEFAULT_DATATYPE_SIZE_OVERRIDES = "";

    @Autowired
    Environment env;

    @Bean
    public DataTypeMappingProvider defaultMappingProvider() {

        String datatypeSizeOverrides = SpringUtils.getProperty(env, DATATYPE_SIZE_OVERRIDES_KEY, DEFAULT_DATATYPE_SIZE_OVERRIDES);

        DefaultDataTypeMappingProvider mappingProvider = new DefaultDataTypeMappingProvider();

        for (String token : CollectionUtils.getTrimmedListFromCSV(datatypeSizeOverrides)) {
            DataType dataType = DataType.valueOf(token);
            mappingProvider.getDataTypeMatches().put(dataType, nullTypeSizeMapping(dataType));
        }

        return mappingProvider;
    }

    protected DataTypeMapping nullTypeSizeMapping(DataType type) {
        DataTypeMapping mapping = new DataTypeMapping();

        mapping.setDataType(type);
        mapping.setTypeSize(null);

        return mapping;
    }

}
