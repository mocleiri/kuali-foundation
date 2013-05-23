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

package org.kuali.common.impex.schemagen;

import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.kuali.common.impex.model.Schema;

public class DefaultPersistSchemaServiceImpl implements PersistSchemaService {

    public void writeSchema(Schema schema, Writer writer) throws SchemaPersistenceException {
        try {
            JAXBContext context = JAXBContext.newInstance(Schema.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(schema, writer);
        } catch (JAXBException e) {
            throw new SchemaPersistenceException("Could not persist given schema", e);
        }
    }

}
