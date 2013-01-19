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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class JdbcUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	public static final BufferedReader getBufferedReader(SqlSource source) throws IOException {
		Assert.notNull(source.getString());
		String encoding = source.getEncoding();
		switch (source.getType()) {
		case LOCATION:
			logger.debug("Opening {}", source.getString());
			return LocationUtils.getBufferedReader(source.getString(), encoding);
		case SQL:
			Assert.notNull(source.getString());
			return LocationUtils.getBufferedReaderFromString(source.getString());
		default:
			throw new IllegalArgumentException("SQL source type '" + source.getType() + "' is unknown");
		}
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
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public static final List<SqlSource> getSqlSources(List<String> locations, String encoding) {
		return getSqlSources(locations, encoding, SqlStringType.LOCATION);
	}

	public static final List<SqlSource> getSqlSources(List<String> strings, String encoding, SqlStringType type) {
		List<SqlSource> sources = new ArrayList<SqlSource>();
		for (String string : strings) {
			SqlSource source = new SqlSource();
			source.setString(string);
			if (encoding != null) {
				source.setEncoding(encoding);
			}
			source.setType(type);
			sources.add(source);
		}
		return sources;
	}

	public static final List<SqlSource> getSqlSourcesFromStrings(List<String> sql) {
		return getSqlSources(sql, null, SqlStringType.SQL);
	}

}
