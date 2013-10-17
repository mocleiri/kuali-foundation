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
package org.kuali.common.jdbc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.jdbc.suppliers.ComplexStringSupplier;
import org.kuali.common.jdbc.suppliers.SimpleStringSupplier;
import org.kuali.common.jdbc.suppliers.SqlLocationContext;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaDataUtils {

	private static final Logger logger = LoggerFactory.getLogger(MetaDataUtils.class);

	public static SqlMetaData getSqlMetaData(String location, SqlLocationContext context) {
		BufferedReader in = null;
		try {
			logger.debug("Getting metadata for [{}] - encoding {}", location, context.getEncoding());
			in = LocationUtils.getBufferedReader(location, context.getEncoding());
			return getSqlMetaData(in, context.getReader());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static SqlMetaData getSqlMetaData(ComplexStringSupplier supplier) {
		long count = 0;
		long size = 0;
		for (String string : supplier.getStrings()) {
			SqlMetaData smd = getSqlMetaData(string, supplier.getReader());
			count += smd.getCount();
			size += smd.getSize();
		}
		return new SqlMetaData(count, size);
	}

	public static SqlMetaData getSqlMetaData(String sql, SqlReader reader) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			return MetaDataUtils.getSqlMetaData(in, reader);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static SqlMetaData getSqlMetaData(BufferedReader in, SqlReader reader) throws IOException {
		long count = 0;
		long size = 0;
		String sql = reader.getSql(in);
		while (sql != null) {
			count++;
			size += sql.length();
			sql = reader.getSql(in);
		}
		return new SqlMetaData(count, size);
	}

	public static SqlMetaData getSqlMetaData(SimpleStringSupplier supplier) {
		List<String> strings = supplier.getStrings();
		int count = strings.size();
		long size = 0;
		for (String string : strings) {
			size += string.length();
		}
		return new SqlMetaData(count, size);
	}

	/**
	 * Return a count of the total number of SQL statements contained in <code>suppliers</code>.
	 */
	public static long getSqlCount(List<SqlSupplier> suppliers) {
		long count = 0;
		for (SqlSupplier supplier : suppliers) {
			SqlMetaData smd = supplier.getMetaData();
			count += smd.getCount();
		}
		return count;
	}

}
