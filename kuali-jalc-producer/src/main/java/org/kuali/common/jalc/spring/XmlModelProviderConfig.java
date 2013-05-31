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

import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kuali.common.jalc.ProducerUtils;
import org.kuali.common.jalc.model.DefaultModelProvider;
import org.kuali.common.jalc.model.ModelProvider;
import org.kuali.common.jalc.model.Schema;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class XmlModelProviderConfig {

    protected final static String XML_LOCATION_KEY = "jalc.schema.location";

    @Autowired
    Environment env;

    @Bean
    public ModelProvider xmlModelProvider() throws JAXBException, IOException {
        String xmlLocation = SpringUtils.getProperty(env, XML_LOCATION_KEY);

        Schema schema = ProducerUtils.unmarshalSchema(xmlLocation);

        return new DefaultModelProvider(schema);
    }

}
