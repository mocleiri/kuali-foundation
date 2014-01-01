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
package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class JdbcUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	/**
	 * Return the total size of the SQL statements contained in <code>sources</code>. Assumes <code>sources</code> has had its
	 * <code>SqlMetaData</code> filled in.
	 */
	public static long getSqlSize(List<SqlSource> sources) {
		long size = 0;
		for (SqlSource source : sources) {
			SqlMetaData smd = source.getMetaData();
			size += smd.getSize();
		}
		return size;
	}

	/**
	 * Return a count of the total number of SQL statements contained in <code>sources</code>. Assumes <code>sources</code> has had its
	 * <code>SqlMetaData</code> filled in.
	 */
	public static long getSqlCount(List<SqlSource> sources) {
		long count = 0;
		for (SqlSource source : sources) {
			SqlMetaData smd = source.getMetaData();
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
			throw new JdbcException(e);
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
			throw new JdbcException(e);
		}
	}

}
