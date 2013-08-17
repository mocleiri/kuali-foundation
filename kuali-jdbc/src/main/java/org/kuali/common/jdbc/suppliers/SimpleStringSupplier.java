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
package org.kuali.common.jdbc.suppliers;

import java.util.List;

import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.util.CollectionUtils;
import org.springframework.util.Assert;

/**
 * Supply SQL from strings that have one SQL statement each
 */
public final class SimpleStringSupplier extends AbstractSupplier {

	private final List<String> strings;
	private boolean closed = true;

	public SimpleStringSupplier(String sql) {
		this(CollectionUtils.singletonList(sql));
	}

	public SimpleStringSupplier(List<String> strings) {
		Assert.notNull(strings);
		this.strings = CollectionUtils.unmodifiableCopy(strings);
	}

	@Override
	public synchronized void open() {
		Assert.isTrue(closed, "Already open");
		this.closed = false;
	}

	@Override
	public synchronized List<String> getSql() {
		if (closed) {
			return null;
		} else {
			this.closed = true;
			return strings;
		}
	}

	public synchronized boolean isClosed() {
		return closed;
	}

	@Override
	public synchronized void close() {
		this.closed = true;
	}

	public List<String> getStrings() {
		return strings;
	}

	@Override
	public synchronized SqlMetaData getMetaData() {
		if (metaData == null) {
			int count = strings.size();
			long size = 0;
			for (String string : strings) {
				size += string.length();
			}
			this.metaData = new SqlMetaData(count, size);
		}
		return this.metaData;
	}

}
