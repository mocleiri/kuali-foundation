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

package org.kuali.common.impex.schema;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.util.LocationUtils;

public class DefaultExportSchemaService implements ExportSchemaService {

	@Override
	public void exportSchema(Schema schema, File file) {
		Writer writer = null;
		try {
			writer = LocationUtils.openWriter(file);
			exportSchema(schema, writer);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	@Override
	public void exportSchema(Schema schema, Writer writer) {
		try {
			// Clone the schema they give us so we don't alter it
			Schema clone = ModelUtils.clone(schema);

			// Null out simple column values that are set to the default
			// ModelUtils.nullOutDefaultColumnValues(clone);

			JAXBContext context = JAXBContext.newInstance(Schema.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(clone, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}
}
