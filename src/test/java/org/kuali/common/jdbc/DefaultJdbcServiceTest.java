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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultJdbcServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcServiceTest.class);
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SqlReader reader = new DefaultSqlReader();
	String vendor = System.getProperty("db.vendor") == null ? "mysql" : System.getProperty("db.vendor");
	boolean mysqlRice = Boolean.getBoolean("mysql.rice");
	Properties properties = getProperties();
	JdbcContext jdbcDba = getJdbcDba();
	JdbcContext jdbcContext = getJdbc();
	String dataThreads = System.getProperty("sql.threads") == null ? getValue("sql.threads") : System.getProperty("sql.threads");

	protected Properties getProperties() {
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties service = PropertyUtils.load("classpath:org/kuali/common/jdbc/service.properties");
		Properties ole = PropertyUtils.load("classpath:ole-fs.properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2, ole, service);
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		properties.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oraperf.ks.kuali.org:1521:ORAPERF");
		properties.setProperty("oracle.dba.username", "master");
		properties.setProperty("oracle.dba.password", "gw570229");
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

	protected String getValue(String key) {
		String original = properties.getProperty(key);
		if (NullUtils.isNullOrNone(original)) {
			return null;
		}
		String resolved = helper.replacePlaceholders(original, properties);
		if (NullUtils.isNullOrNone(resolved)) {
			return null;
		} else {
			return resolved;
		}
	}

	protected JdbcContext getJdbcDba() {
		String url = getValue("jdbc.dba.url");
		String driver = getValue("jdbc.driver");
		String username = getValue("jdbc.dba.username");
		String password = getValue("jdbc.dba.password");
		JdbcContext context = new JdbcContext();
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);
		context.setDataSource(dataSource);
		return context;
	}

	protected JdbcContext getJdbc() {

		String url = getValue("jdbc.url");
		String driver = getValue("jdbc.driver");
		String username = getValue("jdbc.username");
		String password = getValue("jdbc.password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		return context;
	}

	protected ExecutionContext getDbaContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setMessage("Executing DBA SQL");
		ec.setJdbcContext(jdbcDba);
		ec.setReader(reader);
		ec.setSql(Arrays.asList(getValue("sql.drop"), getValue("sql.create")));
		ec.setListener(getDbaListener());
		return ec;
	}

	protected void validateExists(List<String> locations) {
		for (String location : locations) {
			if (!LocationUtils.exists(location)) {
				throw new IllegalArgumentException(location + " does not exist");
			}
		}
	}

	protected List<ExecutionContext> getExecutionContexts(String prefix, int threads) {

		String concurrent = getValue(prefix + ".concurrent");
		String sequential = getValue(prefix + ".sequential");

		String concurrentMsg = getValue(prefix + ".concurrent.message");
		String sequentialMsg = getValue(prefix + ".sequential.message");

		List<String> concurrentLocations = getLocationsFromCSV(concurrent);
		List<String> sequentialLocations = getLocationsFromCSV(sequential);

		validateExists(concurrentLocations);
		validateExists(sequentialLocations);

		String order = getValue(prefix + ".order");
		if (order == null) {
			order = "concurrent,sequential";
		}
		List<String> orderings = CollectionUtils.getTrimmedListFromCSV(order);
		if (orderings.size() != ExecutionMode.values().length) {
			throw new IllegalArgumentException("Only valid values for ordering are " + ExecutionMode.CONCURRENT + " and " + ExecutionMode.SEQUENTIAL);
		}

		ExecutionMode one = ExecutionMode.valueOf(orderings.get(0).toUpperCase());
		ExecutionMode two = ExecutionMode.valueOf(orderings.get(1).toUpperCase());

		// They can't be the same
		if (one.equals(two)) {
			throw new IllegalArgumentException(getInvalidOrderingMessage(order));
		}

		List<ExecutionContext> contexts = new ArrayList<ExecutionContext>();
		ExecutionContext context1 = new ExecutionContext();
		ExecutionContext context2 = new ExecutionContext();

		if (one.equals(ExecutionMode.CONCURRENT)) {
			// Concurrent first, then sequential
			context1.setLocations(concurrentLocations);
			context1.setThreads(threads);
			context1.setMessage(concurrentMsg);
			context2.setLocations(sequentialLocations);
			context2.setMessage(sequentialMsg);
		} else {
			// Sequential first, then concurrent
			context1.setLocations(sequentialLocations);
			context1.setMessage(sequentialMsg);
			context2.setLocations(concurrentLocations);
			context2.setMessage(concurrentMsg);
			context2.setThreads(threads);
		}

		// Add context1 to the list (if it has any locations)
		if (!CollectionUtils.isEmpty(context1.getLocations())) {
			contexts.add(context1);
		}

		// Add context2 to the list (if it has any locations)
		if (!CollectionUtils.isEmpty(context2.getLocations())) {
			contexts.add(context2);
		}

		// Return the list
		return contexts;
	}

	protected String getInvalidOrderingMessage(String order) {
		StringBuilder sb = new StringBuilder();
		sb.append("Ordering [" + order + "] is invalid.  ");
		sb.append("Ordering must be provided as either [" + ExecutionMode.CONCURRENT + "," + ExecutionMode.SEQUENTIAL + "] or ");
		sb.append("[" + ExecutionMode.CONCURRENT + "," + ExecutionMode.SEQUENTIAL + "]");
		return sb.toString();
	}

	protected List<String> getLocationsFromCSV(String csv) {
		// Parse the CSV into a list
		List<String> keys = CollectionUtils.getTrimmedListFromCSV(csv);

		// Allocate some storage for the locations we find
		List<String> locations = new ArrayList<String>();

		// Iterate through the keys
		for (String key : keys) {

			// Extract the value associated with the key
			String value = getValue(key);

			// The properties file is not configured correctly
			if (value == null) {
				throw new IllegalArgumentException("Could not locate a value for [" + key + "]");
			}

			// This key has a value but has been explicitly configured to NONE
			if (NullUtils.isNullOrNone(value)) {
				continue;
			}

			// Are we dealing with a SQL file or a list of SQL files
			if (StringUtils.endsWith(key, ".list")) {
				// If the key we used to look up the value ends with ".list", the value is a resource containing a list of SQL locations
				locations.addAll(LocationUtils.getLocations(value));
			} else {
				// Otherwise, it is a SQL location itself
				locations.add(value);
			}
		}

		// Return the locations we found
		return locations;
	}

	protected NotifyingListener getDefaultListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new ProgressListener());
		listeners.add(new SummaryListener());
		return new NotifyingListener(listeners);
	}

	protected NotifyingListener getDbaListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new LogSqlListener());
		listeners.add(new SummaryListener());
		return new NotifyingListener(listeners);
	}

	@Test
	@Ignore
	public void testReset() {
		try {

			logger.info(getValue("jdbc.url"));
			logger.info(getValue("jdbc.dba.url"));
			logger.info(getValue("jdbc.username"));
			logger.info(getValue("jdbc.password"));
			logger.info(getValue("jdbc.dba.username"));
			logger.info(getValue("jdbc.dba.password"));

			int threads = new Integer(dataThreads);

			List<ExecutionContext> schemas = getExecutionContexts("sql.schema", threads);
			List<ExecutionContext> data = getExecutionContexts("sql.data", threads);
			List<ExecutionContext> constraints = getExecutionContexts("sql.constraints", threads);

			List<ExecutionContext> contexts = new ArrayList<ExecutionContext>();
			contexts.addAll(schemas);
			contexts.addAll(data);
			contexts.addAll(constraints);

			boolean skip = Boolean.getBoolean("sql.skip") || false;

			JdbcService service = new DefaultJdbcService();
			ExecutionContext dba = getDbaContext();
			dba.setExecute(!skip);

			long start = System.currentTimeMillis();
			service.executeSql(dba);
			for (ExecutionContext context : contexts) {
				if (skip) {
					context.setExecute(false);
				}
				context.setEncoding("UTF-8");
				context.setReader(reader);
				context.setJdbcContext(jdbcContext);
				context.setListener(getDefaultListener());
				service.executeSql(context);
			}
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
