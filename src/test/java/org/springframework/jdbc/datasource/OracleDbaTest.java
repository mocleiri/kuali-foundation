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

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.jdbc.service.JdbcUtils.closeQuietly;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection;
import static org.springframework.jdbc.datasource.OracleConnectionContext.newOracleConnectionContextBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.kuali.common.util.encrypt.Encryptor;
import org.slf4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class OracleDbaTest {

	private static final Logger logger = newLogger();

	@Test
	public void testOracle() {
		try {
			List<OracleConnectionContext> contexts = buildContexts();
			for (OracleConnectionContext context : contexts) {
				checkActiveConnections(context);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static String getCurrentSessionsSQL() {
		List<String> sql = newArrayList();
		sql.add("select username");
		sql.add(" , osuser");
		sql.add(" , machine");
		sql.add(" , program");
		sql.add(" , terminal");
		sql.add("from v$session");
		return Joiner.on("").join(sql);
	}

	protected void checkActiveConnections(OracleConnectionContext context) {
		DataSource ds = null;
		Connection conn = null;
		try {
			ds = getDataSource(context);
			conn = doGetConnection(ds);
			Statement stmt = conn.createStatement();
			String sql = getCurrentSessionsSQL();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			throw illegalState(e);
		} finally {
			closeQuietly(ds, conn);
		}
	}

	protected static List<OracleConnectionContext> buildContexts() {
		List<OracleConnectionContext> contexts = newArrayList();
		contexts.add(buildContext("ks"));
		contexts.add(buildContext("rice"));
		contexts.add(buildOLEContext());
		return ImmutableList.copyOf(contexts);
	}

	protected static OracleConnectionContext buildOLEContext() {
		String username = "U2FsdGVkX1962WWPhCy3H9wKqaXkAZ2CRxZk9ORD0Tw=";
		String password = "U2FsdGVkX192QhrhtAYGogxTNBRgeaN0qXnYvbWZaZg=";
		return buildContext("ole", username, password, "OLE");
	}

	protected static OracleConnectionContext buildContext(String project) {
		String username = "U2FsdGVkX1/kkX9m78m2GvhRB+HZ2NTaUB+yNtMi+zQ=";
		String password = "U2FsdGVkX1+MvUNhiDwgoJoiZ6sfVrB7XBB4RkZ97JE=";
		return buildContext(project, username, password, "ORACLE");
	}

	protected static OracleConnectionContext buildContext(String project, String username, String password, String sid) {
		Encryptor enc = getDefaultEncryptor();
		String plaintextUsername = enc.decrypt(username);
		String plaintextPassword = enc.decrypt(password);
		String host = format("oracle.%s.kuali.org", project);
		return newOracleConnectionContextBuilder().withUsername(plaintextUsername).withPassword(plaintextPassword).withHost(host).withSid(sid).build();
	}

	protected static String getUrl(OracleConnectionContext context) {
		return format("jdbc:oracle:thin:@%s:%s:%s", context.getHost(), context.getPort(), context.getSid());
	}

	protected static DataSource getDataSource(OracleConnectionContext context) {
		DriverManagerDataSource dmsd = new DriverManagerDataSource();
		dmsd.setDriverClassName(context.getDriver());
		dmsd.setUrl(getUrl(context));
		dmsd.setUsername(context.getUsername());
		dmsd.setPassword(context.getPassword());
		return dmsd;
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}
