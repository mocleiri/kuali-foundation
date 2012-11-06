package org.kuali.common.jdbc;

import javax.sql.DataSource;

public class JdbcUtils {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
