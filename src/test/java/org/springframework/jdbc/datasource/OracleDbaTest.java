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

import static java.lang.String.format;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;

import javax.sql.DataSource;

import org.junit.Test;
import org.kuali.common.util.encrypt.Encryptor;
import org.slf4j.Logger;

public class OracleDbaTest {

	private static final Logger logger = newLogger();
	private static final String ORACLE_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

	@Test
	public void testOracle() {
		try {
			checkActiveConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void checkActiveConnections() {
		checkActiveConnections(getKSDataSource());
		checkActiveConnections(getOLEDataSource());
		checkActiveConnections(getRiceDataSource());
	}

	protected static void checkActiveConnections(DataSource ds) {
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

	protected static DataSource getOLEDataSource() {
		Encryptor enc = getDefaultEncryptor();
		String username = "U2FsdGVkX1962WWPhCy3H9wKqaXkAZ2CRxZk9ORD0Tw=";
		String password = "U2FsdGVkX192QhrhtAYGogxTNBRgeaN0qXnYvbWZaZg=";
		String url = "jdbc:oracle:thin:@oracle.ole.kuali.org:1521:OLE";
		return getDataSource(url, enc.decrypt(username), enc.decrypt(password));
	}

	protected static DataSource getRiceDataSource() {
		Encryptor enc = getDefaultEncryptor();
		String username = "U2FsdGVkX1/kkX9m78m2GvhRB+HZ2NTaUB+yNtMi+zQ=";
		String password = "U2FsdGVkX1+MvUNhiDwgoJoiZ6sfVrB7XBB4RkZ97JE=";
		String url = "jdbc:oracle:thin:@oracle.rice.kuali.org:1521:ORACLE";
		return getDataSource(url, enc.decrypt(username), enc.decrypt(password));
	}

	protected static DataSource getDataSource(String project, int port, String sid) {
		Encryptor enc = getDefaultEncryptor();
		String username = "U2FsdGVkX1/kkX9m78m2GvhRB+HZ2NTaUB+yNtMi+zQ=";
		String password = "U2FsdGVkX1+MvUNhiDwgoJoiZ6sfVrB7XBB4RkZ97JE=";
		String url = format("jdbc:oracle:thin:@oracle.%s.kuali.org:%s:%s", project, port, sid);
		return getDataSource(url, enc.decrypt(username), enc.decrypt(password));
	}

	protected static DataSource getDataSource(String url, String username, String password) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName(ORACLE_JDBC_DRIVER);
		dmsd.setUrl(url);
		dmsd.setUsername(username);
		dmsd.setPassword(password);
		return dmsd;
	}

}
