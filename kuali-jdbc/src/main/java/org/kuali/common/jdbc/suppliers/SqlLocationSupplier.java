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
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.service.JdbcUtils;
import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

/**
 * Supply SQL from a location containing nothing but SQL statements
 */
public class SqlLocationSupplier extends AbstractSupplier implements LocationSupplier {

	protected BufferedReader in;

	private final String location;
	private final String encoding;
	private final SqlReader reader;
	private SqlMetaData metaData;
	private boolean open;

	public SqlLocationSupplier(String location, String encoding, SqlReader reader) {
		Assert.noBlanks(location, encoding);
		Assert.noNulls(reader);
		Assert.isTrue(LocationUtils.exists(location));
		this.location = location;
		this.encoding = encoding;
		this.reader = reader;
	}

	@Override
	public synchronized void open() throws IOException {
		Assert.isFalse(open, "Already open");
		in = LocationUtils.getBufferedReader(location);
	}

	@Override
	public synchronized List<String> getSql() throws IOException {
		Assert.isTrue(open, "Not open");
		return reader.getSql(in);
	}

	@Override
	public synchronized void close() {
		Assert.isTrue(open, "Not open");
		IOUtils.closeQuietly(in);
		this.open = false;
	}

	@Override
	public synchronized SqlMetaData getMetaData() {
		if (this.metaData == null) {
			this.metaData = JdbcUtils.getSqlMetaDataFromLocation(location, reader);
		}
		return this.metaData;
	}

	@Override
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
