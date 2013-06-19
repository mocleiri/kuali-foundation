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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;

public class ModularSchemaExportExecutable implements Executable {

	public static final Boolean DEFAULT_EXECUTION_SKIP = false;

	protected String outputLocation;

	protected Schema schema;

	protected ExportSchemaService exportService;

	protected boolean skip = DEFAULT_EXECUTION_SKIP;

	protected boolean separateForeignKeys;

	protected String foreignKeyOutputLocation;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		if (separateForeignKeys) {

			// build a new schema object with just the foreign keys from our source schema
			Schema fkSchema = new Schema();
			fkSchema.getForeignKeys().addAll(schema.getForeignKeys());

			// create a new schema object with all other schema elements
			Schema otherSchema = new Schema();
			otherSchema.setSequences(schema.getSequences());
			otherSchema.setTables(schema.getTables());
			otherSchema.setViews(schema.getViews());

			writeSchema(otherSchema, outputLocation);

			writeSchema(fkSchema, foreignKeyOutputLocation);

		} else {
			writeSchema(schema, outputLocation);
		}

	}

	protected void writeSchema(Schema outputSchema, String location) {
		Writer writer;
		try {
			LocationUtils.touch(new File(location));
			writer = new FileWriter(location);
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open a file writer for location " + location, e);
		}
		exportService.exportSchema(outputSchema, writer);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public ExportSchemaService getExportService() {
		return exportService;
	}

	public void setExportService(ExportSchemaService exportService) {
		this.exportService = exportService;
	}

	public String getOutputLocation() {
		return outputLocation;
	}

	public void setOutputLocation(String outputLocation) {
		this.outputLocation = outputLocation;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public boolean isSeparateForeignKeys() {
		return separateForeignKeys;
	}

	public void setSeparateForeignKeys(boolean separateForeignKeys) {
		this.separateForeignKeys = separateForeignKeys;
	}

	public void setForeignKeyOutputLocation(String fkOutpuLocation) {
		this.foreignKeyOutputLocation = fkOutpuLocation;
	}

	public String getForeignKeyOutputLocation() {
		return foreignKeyOutputLocation;
	}
}
