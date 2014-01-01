/**
 * Copyright 2010-2014 The Kuali Foundation
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

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

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

	protected DataSource getOracleDataSource(String url, String username, String password) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dmsd.setUrl(url);
		dmsd.setUsername(username);
		dmsd.setPassword(password);
		return dmsd;
	}

	// @Test
	public void testOracle() {
		try {
			DataSource dataSource = getOracleDataSource("jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE", "master", "gw570229");
			Connection conn = DataSourceUtils.doGetConnection(dataSource);
			logger.info(conn + "");
			// String sql = "{ call rdsadmin.rdsadmin_util.restricted_session(false) }";
			// KSENV4 jeffcaddel administrators-MacBook-Pro-2.local JDBC Thin Client JDBC Thin Client 2013-01-03 15:04:24.0 667 34453
			String sql = "{ call rdsadmin.rdsadmin_util.kill(667,34457) }";
			String nativeSql = conn.nativeSQL(sql);
			logger.info("native sql=" + nativeSql);
			CallableStatement cstmt = conn.prepareCall(sql);
			// cstmt.setString(1, "667");
			// cstmt.setString(2, "34455");
			cstmt.execute();
			conn.commit();
			cstmt.close();
			JdbcUtils.closeQuietly(dataSource, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Test
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
