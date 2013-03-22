package org.kuali.common.jdbc.spring;

import javax.sql.DataSource;

public interface JdbcDbaConfig {

	DataSource jdbcDbaDataSource();

	DataSource jdbcDataSource();

}
