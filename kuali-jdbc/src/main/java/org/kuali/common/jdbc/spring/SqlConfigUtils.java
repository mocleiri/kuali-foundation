/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlExecutionContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.Str;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

public class SqlConfigUtils {

	private static final Logger logger = LoggerFactory.getLogger(SqlConfigUtils.class);

	public static final String SQL_ORDER_KEY = "sql.execution.order";
	public static final String RESOURCES_SUFFIX = ".resources";

	public static JdbcExecutable getJdbcExecutable(SqlConfigContext scc) {
		String skipKey = "jdbc." + scc.getContext().getGroup() + ".skip";

		JdbcContext context = getJdbcContext(scc);
		context.setListener(getSqlListener(scc.getContext().getMode()));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(scc.getEnv(), skipKey, false));
		exec.setService(scc.getCommonConfig().jdbcService());
		exec.setContext(context);
		return exec;
	}

	public static SqlListener getSqlListener(SqlMode mode) {
		switch (mode) {
		case CONCURRENT:
			return new LogSqlListener();
		case SEQUENTIAL:
			List<SqlListener> listeners = new ArrayList<SqlListener>();
			listeners.add(new LogSqlListener());
			listeners.add(new ProgressListener());
			return new NotifyingListener(listeners);
		default:
			throw new IllegalArgumentException("mode [" + mode.name() + "] is unknown");
		}
	}

	public static JdbcContext getJdbcContext(SqlConfigContext scc) {
		SqlExecutionContext ctx = scc.getContext();
		SqlMode mode = ctx.getMode();
		switch (mode) {
		case CONCURRENT:
			return getConcurrentJdbcContext(scc);
		case SEQUENTIAL:
			return getSequentialJdbcContext(scc);
		default:
			throw new IllegalArgumentException("mode [" + mode.name() + "] is unknown");
		}
	}

	public static List<SqlExecutionContext> getSqlExecutionContexts(Environment env) {
		// Extract the CSV value for "sql.execution.order" from the environment
		String csv = SpringUtils.getProperty(env, SQL_ORDER_KEY);

		// NONE or NULL means there is no sql to execute
		if (NullUtils.isNullOrNone(csv)) {
			csv = Str.EMPTY_STRING;
		}

		// Convert the CSV to a list of property keys
		List<String> propertyKeys = CollectionUtils.getTrimmedListFromCSV(csv);

		// Validate that the property keys referenced by sql.execution.order actually exist
		// Validate that all of the resources referenced by any of the properties also exist
		validateSqlExecutionOrderValues(env, propertyKeys);

		// Convert the text values into pojo's
		return getSqlExecutionContexts(propertyKeys);
	}

	public static void validateSqlExecutionOrderValues(Environment env, List<String> propertykeys) {

		// Go through the list of keys and check each one
		for (String propertyKey : propertykeys) {

			// This is a CSV list of actual resources (or .resources files) to load
			String csv = SpringUtils.getProperty(env, propertyKey);

			// Extract the CSV list of resources referenced by this property key
			List<String> resources = CollectionUtils.getTrimmedListFromCSV(csv);

			// Validate that all of the resources exist and that all of the locations referenced by any .resources files also exist
			validateResources(resources);
		}
	}

	public static void validateResources(List<String> resources) {
		// Validate that every resource exists
		for (String resource : resources) {

			// Make sure the .resources file exists
			LocationUtils.validateLocation(resource);

			// Make sure each resource inside the .resources file exists
			if (StringUtils.endsWithIgnoreCase(resource, RESOURCES_SUFFIX)) {
				LocationUtils.validateLocationListing(resource);
			}
		}
	}

	public static List<String> getLocations(Environment env, String key, String suffix) {

		// Allocate some storage for the locations we find
		List<String> locations = new ArrayList<String>();

		// This is a either a list of locations or a location itself
		String value = SpringUtils.getProperty(env, key);

		if (StringUtils.endsWithIgnoreCase(key, suffix)) {
			// If the key ends with .list, it's a list of locations
			locations.addAll(LocationUtils.getLocations(value));
		} else {
			// Otherwise it is a location itself
			locations.add(value);
		}

		// Return the list of locations
		return locations;
	}

	public static List<SqlExecutionContext> getSqlExecutionContexts(List<String> propertyKeys) {
		List<SqlExecutionContext> contexts = new ArrayList<SqlExecutionContext>();
		// Each property key will be something like "sql.schema.concurrent" where the last 2 tokens represent the "group" and the "execution mode"
		for (String propertyKey : propertyKeys) {

			// ["sql", "schema", "concurrent"]
			String[] tokens = StringUtils.split(propertyKey, ".");

			// Must have at least 2 tokens
			Assert.isTrue(tokens.length >= 2, "tokens.length < 2");

			// These are the indexes corresponding to the tokens that indicate group and mode
			int groupIndex = tokens.length - 2;
			int modeIndex = tokens.length - 1;

			// Extract the group (this can be any arbitrary text that indicates some kind of SQL grouping
			String group = StringUtils.trim(tokens[groupIndex]);

			// Extract the mode and convert to upper case so it matches the enum type
			String modeString = StringUtils.trim(tokens[modeIndex].toUpperCase());

			// Convert the mode string to a strongly typed enum object
			SqlMode mode = SqlMode.valueOf(modeString);

			// Create a new context and add it to the list
			contexts.add(new SqlExecutionContext(group, mode));
		}
		return contexts;
	}

	public static DataSummaryListener getConcurrentDataSummaryListener(SqlConfigContext rcc) {
		String propertyPrefix = getPropertyPrefix(rcc);
		String label = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		dsl.setLoggerLevel(LoggerLevel.DEBUG);
		return dsl;
	}

	public static JdbcContext getConcurrentJdbcContext(SqlConfigContext rcc) {
		String threads = SpringUtils.getProperty(rcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(SqlConfigContext rcc) {
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(false);
		ctx.setThreads(1);
		return ctx;
	}

	/**
	 * <pre>
	 *   sql.dba.concurrent
	 *   sql.dba.sequential
	 *   sql.schema.concurrent
	 *   sql.schema.sequential
	 *   sql.constraints.concurrent
	 *   sql.constraints.sequential
	 *   sql.other.concurrent
	 *   sql.other.sequential
	 * </pre>
	 */
	public static String getPropertyPrefix(SqlConfigContext scc) {
		String mode = scc.getContext().getMode().name().toLowerCase();

		StringBuilder sb = new StringBuilder();
		sb.append("sql");
		sb.append(".");
		sb.append(scc.getContext().getGroup());
		sb.append(".");
		sb.append(mode);
		return sb.toString();
	}

	public static LogSqlListener getLogSqlListener(Environment env) {
		String level = SpringUtils.getProperty(env, "sql.log.level", LogSqlListener.DEFAULT_LOGGER_LEVEL.name());
		String mode = SpringUtils.getProperty(env, "sql.log.mode", LogSqlListener.DEFAULT_MODE.name());

		LogSqlListener lsl = new LogSqlListener();
		lsl.setLevel(LoggerLevel.valueOf(level));
		lsl.setMode(LogSqlMode.valueOf(mode));
		return lsl;
	}

	public static NotifyingListener getOtherListener(Environment env) {
		return getSummaryAndProgressListener(env);
	}

	protected static JdbcContext getBaseJdbcContext(SqlConfigContext scc) {
		SqlExecutionContext sec = scc.getContext();
		// dba, schema, data, constraints, other
		String group = sec.getGroup();
		// sql.dba.concurrent, sql.dba.sequential, sql.schema.concurrent, sql.schema.sequential, etc
		String propertyPrefix = getPropertyPrefix(scc);
		String message = "[" + sec.getGroup() + ":" + sec.getMode().name().toLowerCase() + "]";
		boolean skip = SpringUtils.getBoolean(scc.getEnv(), "sql." + group + ".skip", false);
		String key = propertyPrefix + ".trackProgressByUpdateCount";
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(scc.getEnv(), key, false);
		logger.debug("{}={}", key, trackProgressByUpdateCount);
		List<SqlSupplier> suppliers = scc.getCommonConfig().getSqlSuppliers(propertyPrefix);
		DataSource dataSource = scc.getDataSourceConfig().jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(skip);
		ctx.setDataSource(dataSource);
		ctx.setTrackProgressByUpdateCount(trackProgressByUpdateCount);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

	public static NotifyingListener getConstraintsListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener(false, LoggerLevel.DEBUG));
		list.add(getLogSqlListener(env));
		return new NotifyingListener(list);
	}

	public static NotifyingListener getSchemaListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener(false, LoggerLevel.DEBUG));
		list.add(getLogSqlListener(env));
		return new NotifyingListener(list);
	}

	public static NotifyingListener getSummaryAndProgressListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener(true, LoggerLevel.DEBUG));
		list.add(new ProgressListener());
		list.add(getLogSqlListener(env));
		return new NotifyingListener(list);
	}

}
