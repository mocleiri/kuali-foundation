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
import javax.xml.bind.JAXBException;

import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.model.DefaultModelProvider;
import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.spring.SchemaCompareConfig;
import org.kuali.common.impex.model.spring.SchemaCompareExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class XmlSchemaCompareConfig extends SchemaCompareConfig {

    protected static final String SCHEMA_1_LOCATION_KEY = "impex.compare.schema1.location";
    protected static final String SCHEMA_2_LOCATION_KEY = "impex.compare.schema2.location";

    @Autowired
    protected Environment env;

    /**
     * Property key for a boolean setting whether or not the executable should run
     */
    protected static final String EXECUTE_ENABLED_KEY = "impex.compare.execute";

    @Bean(name = "schema1")
    public ModelProvider modelProvider1() {
        try {
            return new DefaultModelProvider(ProducerUtils.unmarshalSchema(SpringUtils.getProperty(env, SCHEMA_1_LOCATION_KEY)));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not build xml model provider for schema 1", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not build xml model provider for schema 1", e);
        }
    }

    @Bean(name = "schema2")
    public ModelProvider modelProvider2() {
        try {
            return new DefaultModelProvider(ProducerUtils.unmarshalSchema(SpringUtils.getProperty(env, SCHEMA_2_LOCATION_KEY)));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not build xml model provider for schema 2", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not build xml model provider for schema 2", e);
        }
    }

    @Bean(initMethod = "execute")
    public SchemaCompareExecutable exportSchemaExecutable() {
        SchemaCompareExecutable result = new SchemaCompareExecutable(executableEnabled());
        result.setConfig(this);
        return result;
    }

    @Bean
    public Boolean executableEnabled() {
        return SpringUtils.getBoolean(env, EXECUTE_ENABLED_KEY, SchemaCompareExecutable.DEFAULT_EXECUTION_SKIP);
    }

    @Override
    public String schema1Label() {
        return SpringUtils.getProperty(env, SCHEMA_1_LABEL_KEY, SCHEMA_1_DEFAULT_LABEL);
    }

    @Override
    public String schema2Label() {
        return SpringUtils.getProperty(env, SCHEMA_2_LABEL_KEY, SCHEMA_2_DEFAULT_LABEL);
    }
}
