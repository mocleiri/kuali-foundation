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

package org.kuali.common.impex.data;

import java.util.List;

import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.impex.data.service.impl.DefaultDumpDataService;
import org.kuali.common.impex.data.service.impl.DumpTableResult;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DataDumpExecutable implements Executable {

	public static final boolean DEFAULT_SKIP_EXECUTION = false;
	public static final DumpDataService DEFAULT_SERVICE = new DefaultDumpDataService();

	boolean skip = DEFAULT_SKIP_EXECUTION;
	DumpDataService service = DEFAULT_SERVICE;
	DumpDataContext context;
	Schema schema;

	@Override
	public void execute() {

		// May have nothing to do
		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(schema, "schema is null");
		Assert.notNull(context, "context is null");
		Assert.notNull(service, "service is null");

		// Connect to the database, extract data from each table and create a .mpx file for each table that has data
		List<DumpTableResult> results = service.dumpTables(context, schema);

		// After exporting tables, store the table statistics
		ExportUtils.storeTableStatistics(results, context.getTableStatisticsLocation());
	}

	public DumpDataContext getContext() {
		return context;
	}

	public void setContext(DumpDataContext context) {
		this.context = context;
	}

	public DumpDataService getService() {
		return service;
	}

	public void setService(DumpDataService service) {
		this.service = service;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public boolean getSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
