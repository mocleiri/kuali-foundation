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
package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

/**
 * Supply SQL from a location containing pre-generated SQL statements
 * 
 * @deprecated
 */
@Deprecated
public class SqlLocationSupplier extends AbstractSupplier implements LocationSupplier {

	private final static String DEFAULT_ENCODING = "UTF-8";

	protected BufferedReader in;

	String location;
	String encoding = DEFAULT_ENCODING;
	SqlReader reader = new DefaultSqlReader();

	public SqlLocationSupplier() {
		this(null);
	}

	public SqlLocationSupplier(String location) {
		super();
		this.location = location;
	}

	@Override
	public void open() throws IOException {
		Assert.hasText(location, "location has no text");
		Assert.notNull(reader, "reader is null");
		in = getLocationReader();
	}

	private BufferedReader getLocationReader() throws IOException {
		return LocationUtils.getBufferedReader(LocationSupplierUtils.getLocationFromContextLocation(location), encoding);
	}

	@Override
	public List<String> getSql() throws IOException {
		return reader.getSql(in);
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(in);
	}

	@Override
	public void fillInMetaData() {
		Assert.hasText(location, "location has no text");
		BufferedReader in = null;
		try {
			in = getLocationReader();
			this.metaData = JdbcUtils.getSqlMetaData(in, reader);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
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
