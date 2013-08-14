/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.model;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.Assert;

public class SqlBucket implements Comparable<SqlBucket> {

	long count;
	long size;
	List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();

	public SqlBucket() {
		this(0, 0, new ArrayList<SqlSupplier>());
	}

	public SqlBucket(long count, long size, List<SqlSupplier> suppliers) {
		Assert.noNulls(suppliers);
		this.count = count;
		this.size = size;
		this.suppliers = suppliers;
	}

	@Override
	public int compareTo(SqlBucket other) {
		return Double.compare(size, other.getSize());
	}

	public long getCount() {
		return count;
	}

	public long getSize() {
		return size;
	}

	public List<SqlSupplier> getSuppliers() {
		return suppliers;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setSuppliers(List<SqlSupplier> suppliers) {
		this.suppliers = suppliers;
	}
}
