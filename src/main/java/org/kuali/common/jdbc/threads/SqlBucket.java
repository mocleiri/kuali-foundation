/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc.threads;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.SqlSource;

public class SqlBucket implements Comparable<SqlBucket> {

	long count;
	long size;
	List<SqlSource> sources = new ArrayList<SqlSource>();

	@Override
	public int compareTo(SqlBucket other) {
		Long size1 = this.size;
		Long size2 = other.getSize();
		return size1.compareTo(size2);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<SqlSource> getSources() {
		return sources;
	}

	public void setSources(List<SqlSource> sources) {
		this.sources = sources;
	}

}
