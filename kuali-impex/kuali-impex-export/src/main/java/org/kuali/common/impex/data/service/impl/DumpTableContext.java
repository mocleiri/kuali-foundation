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

import org.kuali.common.impex.model.Table;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.common.util.Weighted;

public class DumpTableContext implements Comparable<DumpTableContext>, Weighted {

	Table table;
	long rowCount;
	long size;
	PercentCompleteInformer informer;

	@Override
	public double getWeight() {
		return rowCount;
	}

	@Override
	public int compareTo(DumpTableContext other) {
		return Double.compare(rowCount, other.getRowCount());
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}
}
