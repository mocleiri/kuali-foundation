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

import java.util.List;

import org.kuali.common.impex.data.DataExportExecutable;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.ProjectSchemaExportExecutable;
import org.kuali.common.impex.schema.service.SchemaExtractionExecutable;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DatabaseDumpExecutable implements Executable {

	boolean skip;
	Executable showConfigExecutable;
	SchemaExtractionExecutable schemaExtractionExecutable;
	List<ProjectSchemaExportExecutable> projectExportExecutables;
	DataExportExecutable dataExportExecutable;

	@Override
	public void execute() {

		// May have nothing to do
		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(showConfigExecutable, "showConfigExecutable is null");
		Assert.notNull(dataExportExecutable, "dataExportExecutable is null");
		Assert.notNull(schemaExtractionExecutable, "schemaExtractionExecutable is null");
		Assert.notNull(projectExportExecutables, "projectExportExecutables is null");

		// Show the JDBC configuration we are using
		showConfigExecutable.execute();

		// Connect to the database and extract the schema info
		schemaExtractionExecutable.execute();

		// Get the schema model object generated during the extract
		Schema schema = schemaExtractionExecutable.getResult().getSchema();

		// Schema can't be null here
		Assert.notNull(schema, "schema is null");

		// Convert the schema information to XML and store to disk
		for (ProjectSchemaExportExecutable projectExportExecutable : projectExportExecutables) {
			projectExportExecutable.setSchema(schema);
			projectExportExecutable.execute();
		}

		// Connect to the database, extract the data, and persist it to disk
		dataExportExecutable.setSchema(schema);
		dataExportExecutable.execute();
	}

	public DataExportExecutable getDataExportExecutable() {
		return dataExportExecutable;
	}

	public void setDataExportExecutable(DataExportExecutable dataExportExecutable) {
		this.dataExportExecutable = dataExportExecutable;
	}

	public SchemaExtractionExecutable getSchemaExtractionExecutable() {
		return schemaExtractionExecutable;
	}

	public void setSchemaExtractionExecutable(SchemaExtractionExecutable schemaExtractionExecutable) {
		this.schemaExtractionExecutable = schemaExtractionExecutable;
	}

	public Executable getShowConfigExecutable() {
		return showConfigExecutable;
	}

	public void setShowConfigExecutable(Executable showConfigExecutable) {
		this.showConfigExecutable = showConfigExecutable;
	}

	public List<ProjectSchemaExportExecutable> getProjectExportExecutables() {
		return projectExportExecutables;
	}

	public void setProjectExportExecutables(List<ProjectSchemaExportExecutable> projectExportExecutables) {
		this.projectExportExecutables = projectExportExecutables;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
