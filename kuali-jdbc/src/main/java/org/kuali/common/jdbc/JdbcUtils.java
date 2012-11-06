package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcUtils {
	final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	DataSource dataSource;
	SqlGenerator generator;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void readAndExecute(String location) {
		List<String> sql = generator.getSql(location);
		execute(sql);
	}

	public void execute(String sql) {
		execute(Collections.singletonList(sql));
	}

	public void execute(Statement statement, String unparsedSql) throws SQLException {
		List<String> parsedSql = generator.parseSql(unparsedSql);
		for (String s : parsedSql) {
			statement.execute(s);
		}
	}

	public void execute(List<String> sql) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			for (String s : sql) {
				execute(statement, s);
			}
			conn.commit();
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

	public SqlGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(SqlGenerator generator) {
		this.generator = generator;
	}

}
