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

package org.kuali.common.impex.exec;

import org.kuali.common.impex.data.DumpDataExecutable;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.execute.DumpSchemaExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

/**
 * Connect to a database using JDBC, extract schema information needed for Kuali applications, and dump it to disk as XML
 */
public class DumpDatabaseExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;

	public DumpDatabaseExecutable(Executable showConfigExec, ExtractSchemaExecutable extractSchemaExec, DumpSchemaExecutable dumpSchemaExec, DumpDataExecutable dumpDataExec) {
		this(showConfigExec, extractSchemaExec, dumpSchemaExec, dumpDataExec, DEFAULT_SKIP);
	}

	public DumpDatabaseExecutable(Executable showConfigExec, ExtractSchemaExecutable extractSchemaExec, DumpSchemaExecutable dumpSchemaExec, DumpDataExecutable dumpDataExec,
			boolean skip) {
		Assert.noNulls(showConfigExec, extractSchemaExec, dumpSchemaExec, dumpDataExec);
		this.skip = skip;
		this.showConfigExecutable = showConfigExec;
		this.extractSchemaExecutable = extractSchemaExec;
		this.dumpSchemaExecutable = dumpSchemaExec;
		this.dumpDataExecutable = dumpDataExec;
	}

	private final boolean skip;
	private final Executable showConfigExecutable;
	private final ExtractSchemaExecutable extractSchemaExecutable;
	private final DumpSchemaExecutable dumpSchemaExecutable;
	private final DumpDataExecutable dumpDataExecutable;

	@Override
	public void execute() {

		// May have nothing to do
		if (skip) {
			return;
		}

		// Show the JDBC configuration we are using
		showConfigExecutable.execute();

		// Connect to the database and extract the schema info
		extractSchemaExecutable.execute();

		// Get the schema object generated during the extract
		Schema schema = extractSchemaExecutable.getResult().getSchema();

		// Schema can't be null here
		Assert.notNull(schema, "schema is null");

		// Dump the schema to disk as XML
		dumpSchemaExecutable.getRequest().setSchema(schema);
		dumpSchemaExecutable.execute();

		// Connect to the database, extract the data, and dump it to disk
		dumpDataExecutable.setSchema(schema);
		dumpDataExecutable.execute();
	}

	public boolean isSkip() {
		return skip;
	}

	public Executable getShowConfigExecutable() {
		return showConfigExecutable;
	}

	public ExtractSchemaExecutable getExtractSchemaExecutable() {
		return extractSchemaExecutable;
	}

	public DumpSchemaExecutable getDumpSchemaExecutable() {
		return dumpSchemaExecutable;
	}

	public DumpDataExecutable getDumpDataExecutable() {
		return dumpDataExecutable;
	}

}
