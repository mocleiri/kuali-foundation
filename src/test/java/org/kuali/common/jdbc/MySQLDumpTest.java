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
package org.kuali.common.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.DefaultBeanNullifier;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.PropertyPlaceholderHelper;

public class MySQLDumpTest {

	private static final Logger logger = LoggerFactory.getLogger(MySQLDumpTest.class);
	static {
		System.setProperty("sql.execute", Boolean.TRUE.toString());
		System.setProperty("kuali.db", "ks-app-db");
		System.setProperty("kuali.db", "ole-fs");
		System.setProperty("kuali.db", "ks-with-rice-bundled");
		System.setProperty("kuali.db", "mysqldump");
		System.setProperty("db.vendor", "mysql");
	}

	protected Properties loadProperties() {
		String vendor = System.getProperty("db.vendor") == null ? "mysql" : System.getProperty("db.vendor");
		String application = System.getProperty("kuali.db") == null ? "ole-fs" : System.getProperty("kuali.db");
		String execute = System.getProperty("sql.execute") == null ? "true" : System.getProperty("sql.execute");
		boolean mysqlRice = Boolean.getBoolean("mysql.rice");
		if (mysqlRice) {
			logger.info("Connecting to MySQL Rice");
		}
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties service = PropertyUtils.load("classpath:org/kuali/common/jdbc/service.properties");
		Properties app = PropertyUtils.load("classpath:" + application + ".properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2, app, service);
		properties.setProperty("oracle.drop", properties.getProperty("oracle.killAndDrop.rds"));
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		properties.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
		properties.setProperty("oracle.dba.username", "master");
		properties.setProperty("oracle.dba.password", "gw570229");
		properties.setProperty("sql.execute", execute);
		if (mysqlRice) {
			mysqlRice(properties);
		} else {
			mysqlLocalhost(properties);
		}
		return properties;
	}

	protected void mysqlLocalhost(Properties properties) {
		properties.setProperty("mysql.dba.url", "jdbc:mysql://localhost");
		properties.setProperty("mysql.dba.username", "root");
		properties.setProperty("mysql.dba.password", "NONE");
	}

	protected void mysqlRice(Properties properties) {
		properties.setProperty("mysql.dba.url", "jdbc:mysql://mysql.rice.kuali.org");
		properties.setProperty("mysql.dba.username", "master");
		properties.setProperty("mysql.dba.password", "gw570229");
	}

	protected Properties getResolvedProperties(Properties properties) {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties newProperties = new Properties();
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String resolvedValue = helper.replacePlaceholders(originalValue, properties);
			newProperties.setProperty(key, resolvedValue);
		}
		return newProperties;
	}

	protected DatabaseProcessContext getDatbaseProcessContext(Properties p) {
		DatabaseProcessContext dpc = new DatabaseProcessContext();
		dpc.setDbaPassword(p.getProperty("jdbc.dba.password"));
		dpc.setDbaUsername(p.getProperty("jdbc.dba.username"));
		dpc.setPassword(p.getProperty("jdbc.password"));
		dpc.setUsername(p.getProperty("jdbc.username"));
		dpc.setDriver(p.getProperty("jdbc.driver"));
		dpc.setVendor(p.getProperty("jdbc.vendor"));
		dpc.setUrl(p.getProperty("jdbc.url"));
		dpc.setDbaUrl(p.getProperty("jdbc.dba.url"));
		dpc.setVendor(p.getProperty("db.vendor"));
		return dpc;
	}

	protected JdbcContext getDba(Properties p) {
		String url = p.getProperty("jdbc.dba.url");
		String driver = p.getProperty("jdbc.driver");
		String username = p.getProperty("jdbc.dba.username");
		String password = p.getProperty("jdbc.dba.password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		return context;
	}

	protected JdbcContext getNormal(Properties p) {

		String url = p.getProperty("jdbc.url");
		String driver = p.getProperty("jdbc.driver");
		String username = p.getProperty("jdbc.username");
		String password = p.getProperty("jdbc.password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		return context;
	}

	protected void nullify(JdbcContext context) {
		DataSource ds = context.getDataSource();
		DefaultBeanNullifier nullifier = new DefaultBeanNullifier();
		nullifier.setNullTokens(Arrays.asList(Constants.NONE, Constants.NULL));
		nullifier.setProperties(Arrays.asList("password"));
		nullifier.setBean(ds);
		nullifier.nullify();
	}

	@Test
	@Ignore
	public void execute() {
		try {
			Properties original = loadProperties();
			Properties properties = getResolvedProperties(original);
			DatabaseProcessContext dpc = getDatbaseProcessContext(properties);
			JdbcContext dba = getDba(properties);
			JdbcContext normal = getNormal(properties);
			nullify(dba);
			nullify(normal);
			DatabaseResetContext drc = new DatabaseResetContext();
			drc.setExecuteSql(true);
			drc.setDatabaseProcessContext(dpc);
			drc.setDbaJdbcContext(dba);
			drc.setNormalJdbcContext(normal);
			drc.setProperties(properties);
			drc.setReader(new MySQLDumpReader());
			drc.setThreads(5);
			drc.setEncoding("UTF-8");
			String dbaSql = properties.getProperty("sql.drop") + properties.getProperty("sql.create");
			drc.setDbaSql(dbaSql);
			DatabaseService service = new DefaultDatabaseService();
			service.reset(drc);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
