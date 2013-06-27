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

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;

public class ExportSchemaExecutable implements Executable {

	public static final boolean DEFAULT_SKIP_EXECUTION = false;
	public static final ExportSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultExportSchemaService();

	boolean skip = DEFAULT_SKIP_EXECUTION;
	ExportSchemaService exportService = DEFAULT_EXPORT_SCHEMA_SERVICE;

	Map<String, Schema> schemaLocations;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

        Assert.notNull(schemaLocations, "schemaLocations map is null");

		for (String location : schemaLocations.keySet()) {
			Writer writer = null;
			try {
				writer = LocationUtils.openWriter(location);
				Schema schema = schemaLocations.get(location);
				exportService.exportSchema(schema, writer);
			} catch (IOException e) {
				throw new IllegalStateException("Unexpected IO error", e);
			} finally {
				IOUtils.closeQuietly(writer);
			}
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
