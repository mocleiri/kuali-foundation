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
package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

public class LocationSupplier implements SqlSupplier {

	protected BufferedReader in;

	String location;
	String encoding;
	SqlReader reader;

	@Override
	public void open() throws IOException {
		Assert.notNull(location, "location is null");
		Assert.notNull(reader, "reader is null");
		in = LocationUtils.getBufferedReader(location, encoding);
	}

	@Override
	public String getSql() throws IOException {
		return reader.getSqlStatement(in);
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(in);
	}

	@Override
	public SqlMetaData getSqlMetaData() {
		long count = 0;
		long size = 0;

		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, encoding);
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				count++;
				size += sql.length();
				sql = reader.getSqlStatement(in);
			}
			return new SqlMetaData(count, size);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}
}
