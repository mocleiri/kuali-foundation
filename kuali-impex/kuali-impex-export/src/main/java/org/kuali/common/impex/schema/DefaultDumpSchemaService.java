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
import org.kuali.common.impex.schema.execute.DumpSchemaRequest;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.nullify.Nullifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * This service provides methods for dumping a <code>Schema</code> to XML, and for reading in an XML file to create a <code>Schema</code>
 */
public class DefaultDumpSchemaService implements DumpSchemaService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDumpSchemaService.class);

	public void dumpSchema(DumpSchemaRequest context, Schema schema) {

		// Create a filter from the includes/excludes they supplied
		StringFilter filter = StringFilter.getInstance(context.getIncludes(), context.getExcludes());

		// Clone the schema so the act of dumping it to disk does not alter the original schema object they gave us in any way
		Schema clone = new Schema(schema);

		// Filter the schema and keep track of any schema objects that got filtered out
		Schema excludedSchemaObjects = ModelUtils.filter(clone, filter);

		// The full file system path can sometimes be annoyingly long
		String path = FileSystemUtils.getRelativePathQuietly(context.getRelativeDir(), context.getOutputFile());

		// Show what we are up to
		logger.info("Creating - [{}] - {}", path, LoggerUtils.getLogMsg(context.getIncludes(), context.getExcludes()));

		// Log the objects that got filtered out if they asked us to
		if (context.isLogExcludedSchemaObjects()) {
			ModelUtils.logTable(excludedSchemaObjects, "Excluded Schema Objects");
		}

		// Persist the schema to disk as XML
		dumpSchema(clone, context.getOutputFile());
	}

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
			// Clone the schema they give us so the act of persisting it to disk does not alter
			// anything about the object we've been passed
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
			return getSchema(in);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public Schema getSchema(InputStream in) {
		try {
			JAXBContext context = JAXBContext.newInstance(Schema.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Schema) unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@Override
	public Schema getSchema(File file) {
		Assert.notNull(file, "file is null");
		return getSchema(LocationUtils.getCanonicalPath(file));
	}
}
