package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultSqlService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSqlService.class);

	DataSource dataSource;
	SqlReader sqlReader;
	boolean autoCommit;
	boolean showSql;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int getSqlStatementCount(String location) {
		logger.info("Examining - {}", location);
		BufferedReader reader = sqlReader.getSqlReader(location);
		return execute(reader);
	}

	public int getSqlStatementCountFromString(String sql) {
		BufferedReader reader = sqlReader.getSqlReaderFromString(sql);
		return execute(reader);
	}

	protected int getSqlStatementCount(BufferedReader reader) {
		int count = 0;
		try {
			String sql = sqlReader.getSqlStatement(reader);
			while (sql != null) {
				count++;
				sql = sqlReader.getSqlStatement(reader);
			}
			return count;
		} catch (IOException e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	public int executeSQL(String location) {
		logger.info("Executing - {}", location);
		BufferedReader reader = sqlReader.getSqlReader(location);
		return execute(reader);
	}

	public int executeString(String sql) {
		BufferedReader reader = sqlReader.getSqlReaderFromString(sql);
		return execute(reader);
	}

	protected int execute(BufferedReader reader) {
		Connection conn = null;
		Statement statement = null;
		int count = 0;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			conn.setAutoCommit(autoCommit);
			statement = conn.createStatement();
			String sql = sqlReader.getSqlStatement(reader);
			while (sql != null) {
				count++;
				if (showSql) {
					logger.info("{} - [{}]", count, Str.flatten(sql));
				}
				executeSQL(statement, sql);
				sql = sqlReader.getSqlStatement(reader);
			}
			conn.commit();
			return count;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(reader);
			closeQuietly(conn, statement);
		}
	}

	protected void executeSQL(Statement statement, String sql) throws SQLException {
		try {
			statement.execute(sql);
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	protected void closeQuietly(Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(conn);
	}

	protected void closeQuietly(Statement statement) {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	protected void closeQuietly(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	public SqlReader getSqlReader() {
		return sqlReader;
	}

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

}
