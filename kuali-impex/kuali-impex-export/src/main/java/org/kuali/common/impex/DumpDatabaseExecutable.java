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

package org.kuali.common.impex;

import org.kuali.common.impex.data.DataDumpExecutable;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DumpDatabaseExecutable implements Executable {

	boolean skip;
	Executable showConfigExecutable;
	ExtractSchemaExecutable extractSchemaExecutable;
	DataDumpExecutable dataDumpExecutable;

	@Override
	public void execute() {

		// May have nothing to do
		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(showConfigExecutable, "showConfigExecutable is null");
		Assert.notNull(dataDumpExecutable, "dataExportExecutable is null");
		Assert.notNull(extractSchemaExecutable, "schemaExtractionExecutable is null");

		// Show the JDBC configuration we are using
		showConfigExecutable.execute();

		// Connect to the database and extract the schema info
		extractSchemaExecutable.execute();

		// Get the schema model object generated during the extract
		Schema schema = extractSchemaExecutable.getResult().getSchema();

		// Schema can't be null here
		Assert.notNull(schema, "schema is null");

		// Connect to the database, extract the data, and persist it to disk
		dataDumpExecutable.setSchema(schema);
		dataDumpExecutable.execute();
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Executable getShowConfigExecutable() {
		return showConfigExecutable;
	}

	public void setShowConfigExecutable(Executable showConfigExecutable) {
		this.showConfigExecutable = showConfigExecutable;
	}

	public ExtractSchemaExecutable getExtractSchemaExecutable() {
		return extractSchemaExecutable;
	}

	public void setExtractSchemaExecutable(ExtractSchemaExecutable extractSchemaExecutable) {
		this.extractSchemaExecutable = extractSchemaExecutable;
	}

	public DataDumpExecutable getDataDumpExecutable() {
		return dataDumpExecutable;
	}

	public void setDataDumpExecutable(DataDumpExecutable dataDumpExecutable) {
		this.dataDumpExecutable = dataDumpExecutable;
	}

}
