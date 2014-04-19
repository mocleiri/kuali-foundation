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

import static org.kuali.common.jdbc.JdbcUtils.closeQuietly;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.kuali.common.util.encrypt.Encryptor;
import org.slf4j.Logger;

public class OracleDbaTest {

	private static final Logger logger = newLogger();

	private final String username = "U2FsdGVkX1/kkX9m78m2GvhRB+HZ2NTaUB+yNtMi+zQ=";
	private final String password = "U2FsdGVkX1+MvUNhiDwgoJoiZ6sfVrB7XBB4RkZ97JE=";

	protected DataSource getOracleDataSource(String url, String username, String password) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dmsd.setUrl(url);
		dmsd.setUsername(username);
		dmsd.setPassword(password);
		return dmsd;
	}

	@Test
	public void testOracle() {
		try {
			Encryptor enc = getDefaultEncryptor();
			String username = enc.decrypt(this.username);
			String password = enc.decrypt(this.password);
			String url = "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE";
			DataSource dataSource = getOracleDataSource(url, username, password);
			Connection conn = doGetConnection(dataSource);
			closeQuietly(dataSource, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
