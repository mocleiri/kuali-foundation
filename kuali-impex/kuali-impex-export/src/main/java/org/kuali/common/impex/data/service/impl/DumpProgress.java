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

package org.kuali.common.impex.data.service.impl;

import java.io.OutputStream;
import java.util.List;

import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.model.Column;

public class DumpProgress {

	List<List<String>> currentData;
	TableTracker tableTracker;
	OutputStream outputStream;
	List<Column> columns;
	DumpDataContext context;
	DumpTableContext tableContext;

	public List<List<String>> getCurrentData() {
		return currentData;
	}

	public void setCurrentData(List<List<String>> currentData) {
		this.currentData = currentData;
	}

	public TableTracker getTableTracker() {
		return tableTracker;
	}

	public void setTableTracker(TableTracker tableTracker) {
		this.tableTracker = tableTracker;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public DumpDataContext getContext() {
		return context;
	}

	public void setContext(DumpDataContext context) {
		this.context = context;
	}

	public DumpTableContext getTableContext() {
		return tableContext;
	}

	public void setTableContext(DumpTableContext tableContext) {
		this.tableContext = tableContext;
	}

}
