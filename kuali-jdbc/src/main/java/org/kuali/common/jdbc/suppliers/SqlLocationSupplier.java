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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.service.MetaDataUtils;
import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

/**
 * Supply SQL from a location containing nothing but SQL statements
 */
public final class SqlLocationSupplier extends AbstractSupplier implements SqlSupplier {

	public static final int DEFAULT_MAX_COUNT = 50;
	public static final int DEFAULT_MAX_SIZE = 50 * 1024;

	private final String location;
	private final String encoding;
	private final SqlReader reader;
	private final int maxCount;
	private final int maxSize;

	private SqlMetaData metaData;
	private boolean open = false;
	private boolean done = false;
	private BufferedReader in;

	public SqlLocationSupplier(String location, String encoding, SqlReader reader) {
		this(location, encoding, reader, DEFAULT_MAX_COUNT, DEFAULT_MAX_SIZE);
	}

	public SqlLocationSupplier(String location, String encoding, SqlReader reader, int maxCount, int maxSize) {
		Assert.noBlanks(location, encoding);
		Assert.noNulls(reader);
		Assert.isTrue(LocationUtils.exists(location));
		Assert.isTrue(maxCount > 0, "max count must be a positive integer");
		Assert.isTrue(maxSize >= 0, "max size is negative");
		this.location = location;
		this.encoding = encoding;
		this.reader = reader;
		this.maxCount = maxCount;
		this.maxSize = maxSize;
	}

	@Override
	public synchronized void open() throws IOException {
		Assert.isFalse(open, "Already open");
		this.open = true;
		this.done = false;
		this.in = LocationUtils.getBufferedReader(location, encoding);
	}

	@Override
	public synchronized List<String> getSql() throws IOException {
		Assert.isTrue(open, "Not open");
		if (done) {
			return null;
		}
		List<String> sql = getSqlList();
		if (sql.size() == 0) {
			this.done = true;
			return null;
		} else {
			return sql;
		}
	}

	@Override
	public synchronized void close() {
		Assert.isTrue(open, "Not open");
		this.open = false;
		IOUtils.closeQuietly(in);
	}

	protected List<String> getSqlList() throws IOException {
		int count = 0;
		int size = 0;
		List<String> list = new ArrayList<String>();
		String sql = reader.getSql(in);
		while (sql != null) {
			list.add(sql);
			count++;
			size += sql.length();
			if (count > maxCount || size > maxSize) {
				break;
			}
			sql = reader.getSql(in);
		}
		return list;
	}

	@Override
	public synchronized SqlMetaData getMetaData() {
		if (this.metaData == null) {
			this.metaData = MetaDataUtils.getSqlMetaData(this);
		}
		return this.metaData;
	}

	public String getLocation() {
		return location;
	}

	public String getEncoding() {
		return encoding;
	}

	public SqlReader getReader() {
		return reader;
	}

}
