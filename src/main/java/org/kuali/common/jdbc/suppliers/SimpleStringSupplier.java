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

import org.kuali.common.jdbc.service.MetaDataUtils;
import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;

/**
 * Supply SQL from strings that have one SQL statement each
 */
public final class SimpleStringSupplier extends AbstractSupplier {

	private final SqlMetaData metaData;
	private final List<String> strings;
	private boolean open = false;
	private boolean done = false;

	public SimpleStringSupplier(String sql) {
		this(CollectionUtils.singletonList(sql));
	}

	public SimpleStringSupplier(List<String> strings) {
		Assert.notNull(strings);
		this.strings = ListUtils.newImmutableArrayList(strings);
		this.metaData = MetaDataUtils.getSqlMetaData(this);
	}

	@Override
	public synchronized void open() {
		Assert.isFalse(open, "Already open");
		this.open = true;
		this.done = false;
	}

	@Override
	public synchronized List<String> getSql() {
		Assert.isTrue(open, "Not open");
		if (done) {
			return null;
		} else {
			this.done = true;
			return strings;
		}
	}

	@Override
	public synchronized void close() {
		Assert.isTrue(open, "Not open");
		this.open = false;
	}

	@Override
	public SqlMetaData getMetaData() {
		return metaData;
	}

	public List<String> getStrings() {
		return strings;
	}

}
