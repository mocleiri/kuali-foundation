package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcUtils {
	final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	DataSource dataSource;
	SqlReader sqlReader;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void readAndExecute(String location) {
		BufferedReader reader = ResourceUtils.getBufferedReader(location);
		execute(reader);
	}

	public void execute(String sql) {
		BufferedReader reader = ResourceUtils.getBufferedStringReader(sql);
		execute(reader);
	}

	public void execute(BufferedReader reader) {
		Connection conn = null;
		Statement statement = null;
		int count = 1;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			String sql = sqlReader.readSql(reader);
			while (!StringUtils.isBlank(sql)) {
				logger.info(count + " - Executing '" + flatten(sql) + "'");
				if (!statement.execute(sql)) {
					throw new JdbcException("Unable to execute - '" + flatten(sql) + "'");
				}
				count++;
				sql = sqlReader.readSql(reader);
			}
			conn.commit();
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(reader);
			closeQuietly(conn, statement);
		}
	}

	public static final String flatten(String sql) {
		return sql.replace("\r", "CR").replace("\n", "LF");
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
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	protected void closeQuietly(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public SqlReader getSqlReader() {
		return sqlReader;
	}

	public void setSqlReader(SqlReader generator) {
		this.sqlReader = generator;
	}

}
