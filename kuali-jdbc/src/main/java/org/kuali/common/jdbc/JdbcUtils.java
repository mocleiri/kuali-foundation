package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcUtils {
	final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	String encoding;
	DataSource dataSource;
	SqlGenerator generator;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Boolean> readAndExecute(String location) {
		List<String> sql = generator.generateSQL(location);
		return execute(sql);
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

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
