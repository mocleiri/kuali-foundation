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
package org.springframework.jdbc.datasource;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.jdbc.service.JdbcUtils.closeQuietly;
import static org.kuali.common.util.Str.flatten;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.kuali.common.util.encrypt.Encryptor;
import org.slf4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

public class OracleDbaTest {

	private static final Logger logger = newLogger();

	@Test
	public void testOracle() {
		try {
			String query = buildCurrentSessionsQuery();
			List<DataSource> dataSources = buildDataSources();
			List<ExecuteQueryResult> results = executeQuery(dataSources, query);
			showResults(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void showResults(List<ExecuteQueryResult> results) {
		for (ExecuteQueryResult result : results) {
			String query = flatten(result.getQuery());
			Database db = result.getDatabase();
			info("     db: [%s, %s]", db.getUrl(), db.getUsername());
			info("product: [%s, %s]", db.getProduct().getName(), db.getProduct().getVersion());
			info(" driver: [%s, %s]", db.getDriver().getName(), db.getDriver().getVersion());
			info("  query: [%s]", query);
			info("   rows: [%s]", result.getData().size());
			List<Column> columns = result.getColumns();
			for (Column column : columns) {
				info("name=%s", column.getName());
				info("type=%s", column.getType().getCanonicalName());
			}
		}
	}

	protected static List<String> buildCSVFromTable(Table<Integer, Integer, Optional<Object>> table) {
		int rows = table.rowKeySet().size();
		int columns = table.columnKeySet().size();
		List<String> lines = newArrayList();
		for (int row = 0; row < rows; row++) {
			List<String> values = newArrayList();
			for (int column = 0; column < columns; column++) {
				Optional<Object> value = table.get(row, column);
				if (value.isPresent()) {
					values.add(value.get().toString());
				} else {
					values.add("null");
				}
			}
			lines.add(Joiner.on(',').join(values));
		}
		return lines;
	}

	protected static List<ExecuteQueryResult> executeQuery(List<DataSource> dataSources, String query) {
		List<ExecuteQueryResult> results = newArrayList();
		for (DataSource dataSource : dataSources) {
			results.add(executeQuery(dataSource, query));
		}
		return results;
	}

	protected static String buildCurrentlyConnectedUsers() {
		List<String> sql = newArrayList();
		sql.add("select distinct username");
		sql.add("from v$session");
		sql.add("where username is not null");
		sql.add("order by username asc");
		return Joiner.on('\n').join(sql);
	}

	protected static String buildCurrentSessionsQuery() {
		List<String> sql = newArrayList();
		sql.add("select username"); // The Oracle user they are connected as
		sql.add(" , osuser"); // The user they are logged in as on their own machine
		sql.add(" , machine"); // The name of the machine they are logging in from
		sql.add(" , client_info"); // A custom kuali trigger fills this in with the IP address
		sql.add(" , logon_time"); // Contains the time the session was started
		sql.add("from v$session");
		sql.add("where username is not null");
		return Joiner.on('\n').join(sql);
	}

	protected static String buildCurrentSessionsQuery(String username) {
		List<String> sql = newArrayList();
		sql.add("select username"); // The Oracle user they are connected as
		sql.add(" , osuser"); // The user they are logged in as on their own machine
		sql.add(" , machine"); // The name of the machine they are logging in from
		sql.add(" , client_info"); // A custom kuali trigger fills this in with the IP address
		sql.add(" , logon_time"); // Contains the time the session was started
		sql.add("from v$session");
		sql.add(format("where username = '%s'", username));
		return Joiner.on('\n').join(sql);
	}

	protected List<DataSource> buildDataSources() {
		List<OracleConnectionContext> contexts = buildContexts();
		List<DataSource> list = newArrayList();
		for (OracleConnectionContext context : contexts) {
			list.add(buildDataSource(context));
		}
		return ImmutableList.copyOf(list);
	}

	protected static ExecuteQueryResult executeQuery(DataSource ds, String query) {
		Connection conn = null;
		try {
			conn = doGetConnection(ds);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			List<Column> columns = buildColumnMetaData(rs.getMetaData());
			Table<Integer, Integer, Optional<Object>> table = buildTable(rs);
			Database database = getDatabase(conn.getMetaData());
			return ExecuteQueryResult.builder().withDatabase(database).withQuery(query).withColumns(columns).withData(table).build();
		} catch (Exception e) {
			throw illegalState(e);
		} finally {
			closeQuietly(ds, conn);
		}
	}

	protected static Database getDatabase(DatabaseMetaData meta) throws SQLException {
		String url = meta.getURL();
		String username = meta.getUserName();
		Versioned product = Versioned.builder().withName(meta.getDatabaseProductName()).withVersion(meta.getDatabaseProductVersion()).build();
		Versioned driver = Versioned.builder().withName(meta.getDriverName()).withVersion(meta.getDriverVersion()).build();
		return Database.builder().withUrl(url).withUsername(username).withProduct(product).withDriver(driver).build();
	}

	protected static List<Column> buildColumnMetaData(ResultSetMetaData rsmd) throws SQLException, ClassNotFoundException {
		int columns = rsmd.getColumnCount();
		List<Column> list = newArrayList();
		for (int column = 0; column < columns; column++) {
			int columnIndex = column + 1;
			String name = rsmd.getColumnName(columnIndex);
			String className = rsmd.getColumnClassName(columnIndex);
			int nullability = rsmd.isNullable(columnIndex);
			Optional<Boolean> nullable = absent();
			if (ResultSetMetaData.columnNoNulls == nullability) {

			}
			Class<?> type = Class.forName(className);
			Column element = Column.builder().withIndex(column).withName(name).withType(type).build();
			list.add(element);
		}
		return ImmutableList.copyOf(list);
	}

	protected static Optional<Boolean> getNullability(int nullability) {
		switch (nullability) {
		case ResultSetMetaData.columnNoNulls:
			return Optional.of(false);
		case ResultSetMetaData.columnNullable:
			return Optional.of(true);
		case ResultSetMetaData.columnNullableUnknown:
			return absent();
		default:
			throw illegalState("nullability %s is unknown", nullability);
		}
	}

	protected static Table<Integer, Integer, Optional<Object>> buildTable(ResultSet rs) throws SQLException {
		int columns = rs.getMetaData().getColumnCount();
		HashBasedTable<Integer, Integer, Optional<Object>> table = HashBasedTable.create();
		while (rs.next()) {
			addRow(rs, columns, table);
		}
		return ImmutableTable.copyOf(table);
	}

	protected static void addRow(ResultSet rs, int columns, Table<Integer, Integer, Optional<Object>> table) throws SQLException {
		int row = table.rowKeySet().size();
		for (int column = 0; column < columns; column++) {
			int resultSetIndex = column + 1;
			Object object = rs.getObject(resultSetIndex);
			Optional<Object> optional = fromNullable(object);
			table.put(row, column, optional);
		}
	}

	protected static List<OracleConnectionContext> buildContexts() {
		List<OracleConnectionContext> contexts = newArrayList();
		contexts.add(buildContext("ks"));
		contexts.add(buildContext("rice"));
		contexts.add(buildOLEContext());
		return ImmutableList.copyOf(contexts);
	}

	protected static OracleConnectionContext buildOLEContext() {
		String username = "U2FsdGVkX1962WWPhCy3H9wKqaXkAZ2CRxZk9ORD0Tw=";
		String password = "U2FsdGVkX192QhrhtAYGogxTNBRgeaN0qXnYvbWZaZg=";
		return buildContext("ole", username, password, "OLE");
	}

	protected static OracleConnectionContext buildContext(String project) {
		String username = "U2FsdGVkX1/kkX9m78m2GvhRB+HZ2NTaUB+yNtMi+zQ=";
		String password = "U2FsdGVkX1+MvUNhiDwgoJoiZ6sfVrB7XBB4RkZ97JE=";
		return buildContext(project, username, password, "ORACLE");
	}

	protected static OracleConnectionContext buildContext(String project, String username, String password, String sid) {
		Encryptor enc = getDefaultEncryptor();
		String plaintextUsername = enc.decrypt(username);
		String plaintextPassword = enc.decrypt(password);
		String host = format("oracle.%s.kuali.org", project);
		return OracleConnectionContext.builder().withUsername(plaintextUsername).withPassword(plaintextPassword).withHost(host).withSid(sid).build();
	}

	protected static DataSource buildDataSource(OracleConnectionContext context) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName(context.getDriver());
		dmsd.setUrl(getThinOracleJdbcUrl(context.getHost(), context.getPort(), context.getSid()));
		dmsd.setUsername(context.getUsername());
		dmsd.setPassword(context.getPassword());
		return dmsd;
	}

	protected static String getThinOracleJdbcUrl(String host, int port, String sid) {
		return format("jdbc:oracle:thin:@%s:%s:%s", host, port, sid);
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}
