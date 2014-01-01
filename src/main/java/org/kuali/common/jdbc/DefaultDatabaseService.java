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
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.listener.BucketListener;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDatabaseService implements DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseService.class);
	private static final String CONCURRENT = "concurrent";
	private static final String SEQUENTIAL = "sequential";
	private static final String MESSAGE = "message";
	private static final String LIST_SUFFIX = ".list";
	private static final String ORDER = "order";

	@Override
	public void reset(DatabaseResetContext context) {
		DatabaseProcessContext dpc = context.getDatabaseProcessContext();
		logger.info("------------------------------------------------------------------------");
		logger.info("Reset Database - {}", context.getDatabaseProcessContext().getUrl());
		logger.info("------------------------------------------------------------------------");
		logger.info("Vendor - {}", context.getDatabaseProcessContext().getVendor());
		logger.info("URL - {}", context.getDatabaseProcessContext().getUrl());
		logger.info("User - {}", LoggerUtils.getUsername(dpc.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(dpc.getUsername(), dpc.getPassword()));
		logger.info("DBA URL - {}", context.getDatabaseProcessContext().getDbaUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(dpc.getDbaUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(dpc.getDbaUsername(), dpc.getDbaPassword()));
		JdbcMetaData metadata = context.getService().getJdbcMetaData(context.getDbaJdbcContext().getDataSource());
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver - {}", context.getDatabaseProcessContext().getDriver());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("SQL Encoding - {}", context.getEncoding());
		logger.info("------------------------------------------------------------------------");

		int threads = context.getThreads();
		List<ExecutionContext> schemas = getExecutionContexts(context.getSchemaPropertyPrefix(), threads, context.getProperties());
		List<ExecutionContext> data = getExecutionContexts(context.getDataPropertyPrefix(), threads, context.getProperties());
		List<ExecutionContext> constraints = getExecutionContexts(context.getConstraintPropertyPrefix(), threads, context.getProperties());
		List<ExecutionContext> other = getExecutionContexts(context.getOtherPropertyPrefix(), threads, context.getProperties());

		List<ExecutionContext> contexts = new ArrayList<ExecutionContext>();
		contexts.addAll(schemas);
		for (ExecutionContext schema : schemas) {
			schema.setListener(getDDLListener());
		}
		contexts.addAll(data);
		for (ExecutionContext ec : data) {
			ec.setListener(getDMLListener());
		}
		contexts.addAll(constraints);
		for (ExecutionContext ec : constraints) {
			ec.setListener(getDDLListener());
		}

		contexts.addAll(other);
		for (ExecutionContext ec : other) {
			ec.setListener(getOtherListener(true));
		}

		JdbcService service = new DefaultJdbcService();
		ExecutionContext dba = getDbaContext(context);
		dba.setExecute(context.isExecuteSql());
		dba.setReader(context.getDbaReader());

		long start = System.currentTimeMillis();
		service.executeSql(dba);
		for (ExecutionContext ec : contexts) {
			ec.setEncoding(context.getEncoding());
			ec.setReader(context.getReader());
			ec.setJdbcContext(context.getNormalJdbcContext());
			ec.setExecute(context.isExecuteSql());
			service.executeSql(ec);
		}
		logger.info("------------------------------------------------------------------------");
		logger.info("Database Reset Completed");
		logger.info("------------------------------------------------------------------------");
		logger.info("Total time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));
		logger.info("Finished at: {}", new Date());
		logger.info("------------------------------------------------------------------------");
	}

	protected ExecutionContext getDbaContext(DatabaseResetContext context) {
		ExecutionContext ec = new ExecutionContext();
		ec.setMessage("Executing DBA SQL");
		ec.setJdbcContext(context.getDbaJdbcContext());
		ec.setReader(context.getReader());
		ec.setSql(Arrays.asList(context.getDbaSql()));
		ec.setListener(getDbaListener());
		return ec;
	}

	protected NotifyingListener getDefaultListener(boolean showRate) {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new ProgressListener());
		listeners.add(new SummaryListener(showRate));
		return new NotifyingListener(listeners);
	}

	protected NotifyingListener getOtherListener(boolean showRate) {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new ProgressListener());
		listeners.add(new SummaryListener(showRate));
		LogSqlListener lsl = new LogSqlListener();
		lsl.setFlatten(true);
		lsl.setLevel(LoggerLevel.DEBUG);
		listeners.add(lsl);
		return new NotifyingListener(listeners);
	}

	protected NotifyingListener getDDLListener() {
		return getDefaultListener(false);
	}

	protected NotifyingListener getDMLListener() {
		NotifyingListener listener = getDefaultListener(true);
		listener.getListeners().add(new BucketListener());
		return listener;
	}

	protected List<String> getLocationsFromCSV(String csv, String listSuffixPattern, Properties properties) {
		// Parse the CSV into a list
		List<String> keys = CollectionUtils.getTrimmedListFromCSV(csv);

		// Allocate some storage for the locations we find
		List<String> locations = new ArrayList<String>();

		// Iterate through the keys
		for (String key : keys) {

			// Extract the value associated with the key
			String value = properties.getProperty(key);

			// The properties file is not configured correctly
			if (value == null) {
				throw new IllegalArgumentException("Could not locate a value for [" + key + "]");
			}

			// This key has a value but has been explicitly configured to NONE
			if (NullUtils.isNullOrNone(value)) {
				continue;
			}

			// Are we dealing with a SQL file or a list of SQL files
			if (StringUtils.endsWith(key, listSuffixPattern)) {
				// If the key we used to look up the value ends with ".list",
				// the value is a resource containing a list of SQL locations
				locations.addAll(LocationUtils.getLocations(value));
			} else {
				// Otherwise, it is a SQL location itself
				locations.add(value);
			}
		}

		// Return the locations we found
		return locations;
	}

	protected List<ExecutionContext> getExecutionContexts(String prefix, int threads, Properties properties) {

		String concurrent = properties.getProperty(prefix + "." + CONCURRENT);
		String sequential = properties.getProperty(prefix + "." + SEQUENTIAL);

		String concurrentMsg = properties.getProperty(prefix + "." + CONCURRENT + "." + MESSAGE);
		String sequentialMsg = properties.getProperty(prefix + "." + SEQUENTIAL + "." + MESSAGE);

		List<String> concurrentLocations = getLocationsFromCSV(concurrent, LIST_SUFFIX, properties);
		List<String> sequentialLocations = getLocationsFromCSV(sequential, LIST_SUFFIX, properties);

		validateExists(concurrentLocations);
		validateExists(sequentialLocations);

		String order = properties.getProperty(prefix + "." + ORDER);
		if (order == null) {
			order = CONCURRENT + "," + SEQUENTIAL;
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

	protected NotifyingListener getDbaListener() {
		LogSqlListener lsl = new LogSqlListener();
		lsl.setLevel(LoggerLevel.INFO);
		lsl.setFlatten(true);
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(lsl);
		listeners.add(new SummaryListener(false));
		return new NotifyingListener(listeners);
	}

	protected void validateExists(List<String> locations) {
		for (String location : locations) {
			if (!LocationUtils.exists(location)) {
				throw new IllegalArgumentException(location + " does not exist");
			}
		}
	}

	protected String getInvalidOrderingMessage(String order) {
		StringBuilder sb = new StringBuilder();
		sb.append("Ordering [" + order + "] is invalid.  ");
		sb.append("Ordering must be provided as either [" + ExecutionMode.CONCURRENT + "," + ExecutionMode.SEQUENTIAL + "] or ");
		sb.append("[" + ExecutionMode.CONCURRENT + "," + ExecutionMode.SEQUENTIAL + "]");
		return sb.toString();
	}

}
