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

import org.codehaus.plexus.util.StringUtils;
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
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

public class SqlConfigUtils {

	private static final Logger logger = LoggerFactory.getLogger(SqlConfigUtils.class);

	public static final String SQL_PREFIX = "sql";
	public static final String SQL_ORDER_KEY = "sql.execution.order";

	public static List<SqlExecutionContext> getSqlExecutionContexts(Environment env) {
		String csv = SpringUtils.getProperty(env, SQL_ORDER_KEY);
		List<String> values = CollectionUtils.getTrimmedListFromCSV(csv);
		return getSqlExecutionContexts(values);
	}

	public static String getPropertyKey(SqlExecutionContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append(SQL_PREFIX);
		sb.append(".");
		sb.append(context.getGroup());
		sb.append(".");
		sb.append(context.getMode().name());
		return sb.toString().toLowerCase();
	}

	public static List<SqlExecutionContext> getSqlExecutionContexts(List<String> values) {
		List<SqlExecutionContext> list = new ArrayList<SqlExecutionContext>();
		for (String value : values) {
			String[] tokens = StringUtils.split(value, ".");
			Assert.isTrue(tokens.length == 2, "tokens.length != 2");
			String group = StringUtils.trim(tokens[0]);
			String modeString = StringUtils.trim(tokens[1].toUpperCase());
			SqlMode mode = SqlMode.valueOf(modeString);
			SqlExecutionContext context = new SqlExecutionContext();
			context.setGroup(group);
			context.setMode(mode);
			list.add(context);
		}
		return list;
	}

	public static DataSummaryListener getConcurrentDataSummaryListener(ResetConfigContext rcc) {
		String propertyPrefix = getPropertyPrefix(rcc);
		String label = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		dsl.setLoggerLevel(LoggerLevel.DEBUG);
		return dsl;
	}

	public static JdbcContext getConcurrentJdbcContext(ResetConfigContext rcc) {
		String threads = SpringUtils.getProperty(rcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(ResetConfigContext rcc) {
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
	public static String getPropertyPrefix(ResetConfigContext rcc) {
		String mode = rcc.getMode().name().toLowerCase();

		StringBuilder sb = new StringBuilder();
		sb.append("sql");
		sb.append(".");
		sb.append(rcc.getType());
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

	protected static JdbcContext getBaseJdbcContext(ResetConfigContext rcc) {
		// dba, schema, data, constraints, other
		String type = rcc.getType();
		// sql.dba.concurrent, sql.dba.sequential, sql.schema.concurrent, sql.schema.sequential, etc
		String propertyPrefix = getPropertyPrefix(rcc);
		String message = "[" + rcc.getType() + ":" + rcc.getMode().name().toLowerCase() + "]";
		boolean skip = SpringUtils.getBoolean(rcc.getEnv(), "sql." + type + ".skip", false);
		String key = propertyPrefix + ".trackProgressByUpdateCount";
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(rcc.getEnv(), key, false);
		logger.debug("{}={}", key, trackProgressByUpdateCount);
		List<SqlSupplier> suppliers = rcc.getCommonConfig().getSqlSuppliers(propertyPrefix);
		DataSource dataSource = rcc.getDataSourceConfig().jdbcDataSource();

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
