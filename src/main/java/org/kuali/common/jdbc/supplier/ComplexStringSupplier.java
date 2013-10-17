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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

/**
 * Supply SQL from strings that may have more than one SQL statement each
 * 
 * @deprecated
 */
@Deprecated
public class ComplexStringSupplier extends AbstractSupplier {

	protected int index = 0;
	protected BufferedReader in;

	List<String> strings;
	SqlReader reader = new DefaultSqlReader();

	public ComplexStringSupplier() {
		this((String) null);
	}

	public ComplexStringSupplier(String sql) {
		this(Arrays.asList(sql));
	}

	public ComplexStringSupplier(List<String> strings) {
		super();
		this.strings = strings;
	}

	@Override
	public void open() {

		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");

		// Reset index to zero
		index = 0;

		// Open a reader to the first string in the list
		in = getBufferedReader(strings, index);
	}

	@Override
	public List<String> getSql() {
		try {
			// Have the reader produce a SQL statement
			List<String> sql = reader.getSql(in);

			if (sql != null) {
				// We got a SQL statement we are done
				return sql;
			} else {
				// We've exhausted the current string, move to the next one
				index++;
			}

			// We've exhausted all of the strings, we are done
			if (index == strings.size()) {
				return null;
			}

			// Open a reader to the new string
			in = getBufferedReader(strings, index);

			// Get a SQL statement from the new string
			return getSql();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Extract a String from the list and open a BufferedReader that can read from it
	 */
	protected BufferedReader getBufferedReader(List<String> strings, int index) {
		String string = strings.get(index);
		return LocationUtils.getBufferedReaderFromString(string);
	}

	@Override
	public void close() {
		// Reset index to zero
		index = 0;

		// Make sure the BufferedReader is closed
		IOUtils.closeQuietly(in);
	}

	@Override
	public void fillInMetaData() {
		long count = 0;
		long size = 0;
		for (String string : strings) {
			SqlMetaData smd = getMetaData(string);
			count += smd.getCount();
			size += smd.getSize();
		}
		this.metaData = new SqlMetaData(count, size);
	}

	protected SqlMetaData getMetaData(String sql) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			return JdbcUtils.getSqlMetaData(in, reader);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

}
