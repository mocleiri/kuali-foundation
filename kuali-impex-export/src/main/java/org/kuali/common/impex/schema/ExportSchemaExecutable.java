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
import java.util.Map;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;

public class ExportSchemaExecutable implements Executable {

	public static final boolean DEFAULT_SKIP_EXECUTION = false;
	public static final ExportSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultExportSchemaService();

	boolean skip = DEFAULT_SKIP_EXECUTION;
	ExportSchemaService exportService = DEFAULT_EXPORT_SCHEMA_SERVICE;

	Map<String, Schema> schemaLocations;

	public ExportSchemaExecutable() {
		this(DEFAULT_SKIP_EXECUTION);
	}

	public ExportSchemaExecutable(boolean skip) {
		this.skip = skip;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		for (String location : schemaLocations.keySet()) {
			Writer writer;
			try {
				LocationUtils.touch(new File(location));
				writer = new FileWriter(location);
			} catch (IOException e) {
				throw new IllegalArgumentException("Could not open a file writer for location " + location, e);
			}
			exportService.exportSchema(schemaLocations.get(location), writer);
		}

	}

	public Map<String, Schema> getSchemaLocations() {
		return schemaLocations;
	}

	public void setSchemaLocations(Map<String, Schema> schemaLocations) {
		this.schemaLocations = schemaLocations;
	}

	public ExportSchemaService getExportService() {
		return exportService;
	}

	public void setExportService(ExportSchemaService exportService) {
		this.exportService = exportService;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
