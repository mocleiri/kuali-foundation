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
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);

	@Override
    public List<ExecutionMetaData> executeSql(JdbcContext context, List<String> locations, String encoding) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			boolean originalAutoCommitSetting = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			for (int i = 0; i < locations.size(); i++) {
				String location = locations.get(i);
				SqlSourceExecutionContext sec = getSourceSqlExecutionContext(context, conn, statement, source, count, i, sources.size());
				SqlMetaData smd = executeSqlFromSource(sec);
			}
			conn.setAutoCommit(originalAutoCommitSetting);
			return new ArrayList<ExecutionMetaData>();
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	@Override
	public JdbcMetaData getJdbcMetaData(DataSource dataSource) {
		Connection conn = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			DatabaseMetaData dbmd = conn.getMetaData();
			return getJdbcMetaData(dbmd);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			logger.trace("closing connection");
			JdbcUtils.closeQuietly(dataSource, conn);
		}
	}

	@Override
	public List<SqlMetaData> getMetaData(SqlReader reader, List<String> locations, String encoding) {
		List<SqlMetaData> smdl = new ArrayList<SqlMetaData>();
		for (String location : locations) {
			smdl.add(getMetaData(reader, location, encoding));
		}
		return smdl;
	}

	@Override
	public SqlMetaData getMetaData(SqlReader reader, String location, String encoding) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, encoding);
			return getSqlMetaData(reader, in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO exception");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public List<SqlMetaData> getMetaDataFromStrings(SqlReader reader, List<String> sql) {
		List<SqlMetaData> smdl = new ArrayList<SqlMetaData>();
		for (String s : sql) {
			smdl.add(getMetaDataFromString(reader, s));
		}
		return smdl;
	}

	@Override
	public SqlMetaData getMetaDataFromString(SqlReader reader, String sql) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			return getSqlMetaData(reader, in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO exception");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected SqlMetaData getSqlMetaData(SqlReader reader, BufferedReader in) throws IOException {
		long count = 0;
		long size = 0;
		String sql = reader.getSqlStatement(in);
		while (sql != null) {
			count++;
			size += sql.length();
			sql = reader.getSqlStatement(in);
		}
		SqlMetaData smd = new SqlMetaData();
		smd.setCount(count);
		smd.setSize(size);
		return smd;
	}

	protected JdbcMetaData getJdbcMetaData(DatabaseMetaData dbmd) throws SQLException {
		JdbcMetaData md = new JdbcMetaData();
		md.setDatabaseProductName(dbmd.getDatabaseProductName());
		md.setDatabaseProductVersion(dbmd.getDatabaseProductVersion());
		md.setDriverName(dbmd.getDriverName());
		md.setDriverVersion(dbmd.getDriverVersion());
		md.setUrl(dbmd.getURL());
		md.setUsername(dbmd.getUserName());
		return md;
	}
}
