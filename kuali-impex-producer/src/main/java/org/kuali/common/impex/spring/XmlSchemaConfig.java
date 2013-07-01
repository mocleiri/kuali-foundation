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
import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class XmlSchemaConfig {

    protected final static String XML_LOCATION_KEY = "impex.schema.location";

    @Autowired
    Environment env;

    @Bean
    public Schema schema() throws JAXBException, IOException {
        String xmlLocation = SpringUtils.getProperty(env, XML_LOCATION_KEY);

        return ProducerUtils.unmarshalSchema(xmlLocation);
    }

}
