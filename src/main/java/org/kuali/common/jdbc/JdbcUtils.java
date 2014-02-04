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
package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class JdbcUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	/**
	 * Return the total size of the SQL statements contained in <code>suppliers</code>. Assumes <code>suppliers</code> has had its <code>SqlMetaData</code> filled in.
	 */
	public static long getSqlSize(List<SqlSupplier> suppliers) {
		long size = 0;
		for (SqlSupplier supplier : suppliers) {
			SqlMetaData smd = supplier.getMetaData();
			size += smd.getSize();
		}
		return size;
	}

	/**
	 * Return a count of the total number of SQL statements contained in <code>suppliers</code>. Assumes <code>suppliers</code> has had its <code>SqlMetaData</code> filled in.
	 */
	public static long getSqlCount(List<SqlSupplier> suppliers) {
		long count = 0;
		for (SqlSupplier supplier : suppliers) {
			SqlMetaData smd = supplier.getMetaData();
			count += smd.getCount();
		}
		return count;
	}

	public static final void closeQuietly(DataSource dataSource, Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(dataSource, conn);
	}

	public static final void closeQuietly(Statement statement) {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static final void closeQuietly(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static final void closeQuietly(DataSource dataSource, Connection conn) {
		if (conn == null && dataSource == null) {
			return;
		}
		Assert.notNull(dataSource, "dataSource is null but conn is not");
		try {
			logger.trace("releasing connection");
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static SqlMetaData getSqlMetaData(BufferedReader in, org.kuali.common.jdbc.SqlReader reader) throws IOException {
		long count = 0;
		long size = 0;
		List<String> sql = reader.getSql(in);
		while (sql != null) {
			for (String s : sql) {
				count++;
				size += s.length();
			}
			sql = reader.getSql(in);
		}
		return new SqlMetaData(count, size);
	}

}
