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
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;

/**
 * Supply SQL from strings that may have more than one SQL statement each
 */
public final class ComplexStringSupplier extends AbstractSupplier {

	private int index = 0;
	private BufferedReader in;

	private final List<String> strings;
	private final SqlReader reader;
	private final SqlMetaData metaData;

	public ComplexStringSupplier(String sql, SqlReader reader) {
		this(CollectionUtils.singletonList(sql), reader);
	}

	public ComplexStringSupplier(List<String> strings, SqlReader reader) {
		Assert.noNulls(strings, reader);
		this.strings = strings;
		this.reader = reader;
		this.metaData = JdbcUtils.getSqlMetaData(strings, reader);
	}

	@Override
	public void open() {
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
	public SqlMetaData getMetaData() {
		return metaData;
	}

	public List<String> getStrings() {
		return strings;
	}

	public SqlReader getReader() {
		return reader;
	}

}
