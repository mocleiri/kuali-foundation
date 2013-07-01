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

import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.spring.SchemaCompareConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Configuration
public class XmlSchemaCompareConfig extends SchemaCompareConfig {

    protected static final String SCHEMA_1_LOCATION_KEY = "impex.compare.schema1.location";
    protected static final String SCHEMA_2_LOCATION_KEY = "impex.compare.schema2.location";

    @Autowired
    protected Environment env;

    @Bean(name = "schema1")
    public Schema schema1() {
        try {
            return ProducerUtils.unmarshalSchema(SpringUtils.getProperty(env, SCHEMA_1_LOCATION_KEY));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not build xml model provider for schema 1", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not build xml model provider for schema 1", e);
        }
    }

    @Bean(name = "schema2")
    public Schema schema2() {
        try {
            return ProducerUtils.unmarshalSchema(SpringUtils.getProperty(env, SCHEMA_2_LOCATION_KEY));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not build xml model provider for schema 2", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not build xml model provider for schema 2", e);
        }
    }

}
