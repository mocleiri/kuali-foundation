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

import java.io.IOException;
import java.util.Map;
import javax.xml.bind.JAXBException;

import org.kuali.common.impex.schema.SchemaSqlProducer;
import org.kuali.common.impex.schema.impl.SchemaXmlLocationSupplier;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SchemaSqlProducerConfig.class, JdbcCommonConfig.class})
public class SchemaXmlSupplierConfig {

    protected static final String SCHEMA_SUFFIX = "schema.xml";

    @Autowired
    SchemaSqlProducer sqlProducer;

    @Autowired
    JdbcCommonConfig jdbcCommonConfig;

    @Bean
    public Map<String, LocationSupplierSourceBean> schemaXmlExtensionMappings() throws JAXBException, IOException {
        SchemaXmlLocationSupplier supplier = new SchemaXmlLocationSupplier();
        supplier.setProducer(sqlProducer);

        // This hands out clones of MpxLocationSupplier, one for every .mpx file being parsed
        LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
        lssb.setSupplierClass(SchemaXmlLocationSupplier.class);
        lssb.setSupplierInstance(supplier);

        // Add "schema.xml" as an filename suffix JDBC can handle
        Map<String, LocationSupplierSourceBean> mappings = jdbcCommonConfig.jdbcExtensionMappings();
        mappings.put(SCHEMA_SUFFIX, lssb);
        return mappings;
    }

}
