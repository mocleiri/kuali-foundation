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

import org.kuali.common.impex.data.SqlProducer;
import org.kuali.common.impex.data.impl.MpxLocationSupplier;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, BatchConfig.class, JdbcDataSourceConfig.class, XmlSchemaConfig.class })
public class MpxSupplierConfig {

    @Autowired
    Environment env;

    @Autowired
    JdbcCommonConfig jdbcCommonConfig;

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Autowired
    BatchConfig batchConfig;

    @Autowired
    XmlSchemaConfig modelProviderConfig;

    protected static final String PRODUCER_IMPL_KEY = "producer.sql.mpx.impl";

    @Bean
    public SqlProducer impexProducer() {
        SqlProducer producer = SpringUtils.getInstance(env, PRODUCER_IMPL_KEY);

        producer.setBatchDataSizeLimit(batchConfig.impexBatchSize());
        producer.setBatchRowCountLimit(batchConfig.impexBatchRows());

        return producer;
    }

    @Bean
    public Map<String, LocationSupplierSourceBean> impexExtensionMappings() throws JAXBException, IOException {
        // This gets cloned for each .mpx file
        MpxLocationSupplier mls = new MpxLocationSupplier();
        mls.setSchema(modelProviderConfig.schema());
        mls.setProducer(impexProducer());

        // This hands out clones of MpxLocationSupplier, one for every .mpx file being parsed
        LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
        lssb.setSupplierClass(MpxLocationSupplier.class);
        lssb.setSupplierInstance(mls);

        // Add mpx as an extension JDBC can handle
        Map<String, LocationSupplierSourceBean> mappings = jdbcCommonConfig.jdbcExtensionMappings();
        mappings.put("mpx", lssb);
        return mappings;
    }

}
