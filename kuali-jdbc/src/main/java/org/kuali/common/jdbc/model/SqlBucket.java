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

import java.util.List;

import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class SqlBucket implements Comparable<SqlBucket> {

	public SqlBucket(long count, long size, List<SqlSupplier> suppliers) {
		Assert.noNulls(suppliers);
		this.count = count;
		this.size = size;
		this.suppliers = CollectionUtils.unmodifiableCopy(suppliers);
	}

	private final long count;
	private final long size;
	private final List<SqlSupplier> suppliers;

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
}
