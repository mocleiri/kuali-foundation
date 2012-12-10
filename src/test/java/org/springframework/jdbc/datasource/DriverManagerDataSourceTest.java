package org.springframework.jdbc.datasource;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.kuali.common.jdbc.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManagerDataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(DriverManagerDataSourceTest.class);

	protected DataSource getMySQLDataSource(String url, String username, String password) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName("com.mysql.jdbc.Driver");
		dmsd.setUrl(url);
		dmsd.setUsername(username);
		dmsd.setPassword(password);
		return dmsd;
	}

	@Test
	public void test() {
		try {
			DataSource dataSource = getMySQLDataSource("jdbc:mysql://localhost", "root", null);
			Connection conn = DataSourceUtils.doGetConnection(dataSource);
			logger.info(conn + "");
			JdbcUtils.closeQuietly(dataSource, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
