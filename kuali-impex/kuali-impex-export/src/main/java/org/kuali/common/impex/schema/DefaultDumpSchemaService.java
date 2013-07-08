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
import java.io.InputStream;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.model.util.SchemaNullifier;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.Nullifier;
import org.springframework.util.Assert;

/**
 * This service provides methods for reading/persisting a Schema object to/from XML.
 */
public class DefaultDumpSchemaService implements DumpSchemaService {

	@Override
	public void dumpSchema(Schema schema, File file) {
		Writer writer = null;
		try {
			writer = LocationUtils.openWriter(file);
			dumpSchema(schema, writer);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	@Override
	public void dumpSchema(Schema schema, Writer writer) {
		try {
			// Clone the schema they give us so we don't alter it
			Schema clone = new Schema(schema);

			// Null out values that don't need to be converted to XML
			// Mostly these are primitive types that are still set to their default value
			Nullifier nullifier = new SchemaNullifier(clone);
			nullifier.nullify();

			JAXBContext context = JAXBContext.newInstance(Schema.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(clone, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@Override
	public Schema getSchema(String location) {
		Assert.notNull(location, "location is null");
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			JAXBContext context = JAXBContext.newInstance(Schema.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Schema schema = (Schema) unmarshaller.unmarshal(in);
			ModelUtils.fillInSchema(schema);
			return schema;
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public Schema getSchema(File file) {
		Assert.notNull(file, "file is null");
		return getSchema(LocationUtils.getCanonicalPath(file));
	}
}
