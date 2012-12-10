package org.springframework.jdbc.datasource;

import org.junit.Test;

public class DriverManagerDataSourceTest {

	@Test
	public void test() {
		try {
			DriverManagerDataSource dmsd = new DriverManagerDataSource();
			dmsd.setDriverClassName("com.mysql.jdbc.Driver");
			dmsd.setUrl("jdbc:mysql://localhost");
			dmsd.setUsername("root");
			dmsd.setPassword(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
