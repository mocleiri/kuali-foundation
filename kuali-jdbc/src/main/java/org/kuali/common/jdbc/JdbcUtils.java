package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcUtils {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean execute(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			ps = conn.prepareStatement(sql);
			return ps.execute();
		} catch (SQLException e) {
			throw new JdbcException(e);
		} finally {
			closeQuietly(conn, ps);
		}
	}

	protected void closeQuietly(Connection conn, PreparedStatement ps) {
		closeQuietly(ps);
		closeQuietly(conn);
	}

	protected void closeQuietly(PreparedStatement ps) {
		try {
			ps.close();
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

}
