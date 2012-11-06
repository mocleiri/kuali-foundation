package org.kuali.common.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcUtils {

	private static final String DEFAULT_DELIMITER = "/";
	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

	String encoding;
	DataSource dataSource;
	String delimiter = DEFAULT_DELIMITER;
	String lineSeparator = DEFAULT_LINE_SEPARATOR;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected List<String> getSql(String location, String encoding, String delimiter, String lineSeparator) {
		try {
			List<String> lines = ResourceUtils.getLines(location, encoding);
			List<String> sql = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				String trimmed = line.trim();
				if (delimiter.equals(trimmed)) {
					sql.add(sb.toString());
					sb = new StringBuilder();
				} else {
					sb.append(line + lineSeparator);
				}
			}
			return sql;
		} catch (IOException e) {
			throw new JdbcException(e);
		}
	}

	public boolean execute(String sql) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			statement = conn.createStatement();
			return statement.execute(sql);
		} catch (SQLException e) {
			throw new JdbcException(e);
		} finally {
			closeQuietly(conn, statement);
		}
	}

	public List<Boolean> execute(List<String> sql) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			conn.setAutoCommit(false);
			List<Boolean> executed = new ArrayList<Boolean>();
			for (String s : sql) {
				statement = conn.createStatement();
				executed.add(statement.execute(s));
			}
			conn.commit();
			return executed;
		} catch (SQLException e) {
			throw new JdbcException(e);
		} finally {
			closeQuietly(conn, statement);
		}
	}

	protected void closeQuietly(Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(conn);
	}

	protected void closeQuietly(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public void closeQuietly(Connection conn) {
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
