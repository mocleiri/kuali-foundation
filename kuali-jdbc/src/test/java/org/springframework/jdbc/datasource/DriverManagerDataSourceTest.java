/**
 * Copyright 2010-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
