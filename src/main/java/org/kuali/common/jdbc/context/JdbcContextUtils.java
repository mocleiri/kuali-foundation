/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.jdbc.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.kuali.common.jdbc.GroupedSqlConfig;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.jdbc.supplier.LocationSuppliersFactoryBean;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * This class contains utility methods for creating a JdbcContext
 * 
 * @author andrewlubbers
 */
public class JdbcContextUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcContextUtils.class);

	public static final String THREAD_COUNT_KEY = "sql.threads";
	public static final int DEFAULT_CONCURRENT_THREAD_COUNT = 4;
	public static final String SQL_LOG_LEVEL_KEY = "sql.log.level";
	public static final String SQL_LOG_MODE_KEY = "sql.log.mode";
	public static final String MESSAGE_PROPERTY_SUFFIX = ".message";
	private static final String SKIP_PROPERTY_SUFFIX = ".skip";
	public static final String TRACK_PROGRESS_PROPERTY_SUFFIX = ".trackProgressByUpdateCount";

	public static JdbcContext buildJdbcContext(DataSource dataSource, List<SqlSupplier> suppliers) {
		return buildJdbcContext(null, false, false, dataSource, suppliers, SqlMode.SEQUENTIAL, null);
	}

	public static JdbcContext buildJdbcContext(DataSource dataSource, List<SqlSupplier> suppliers, SqlMode mode) {
		return buildJdbcContext(null, false, false, dataSource, suppliers, mode, null);
	}

	public static JdbcContext buildJdbcContext(DataSource dataSource, List<SqlSupplier> suppliers, Environment env) {
		return buildJdbcContext(null, false, false, dataSource, suppliers, SqlMode.SEQUENTIAL, env);
	}

	public static JdbcContext buildJdbcContext(DataSource dataSource, List<SqlSupplier> suppliers, SqlMode mode, Environment env) {
		return buildJdbcContext(null, false, false, dataSource, suppliers, mode, env);
	}

	public static JdbcContext buildJdbcContext(String message, boolean skip, boolean trackProgressByUpdateCount, DataSource dataSource, List<SqlSupplier> suppliers, SqlMode mode,
			Environment env) {
		JdbcContext result = new JdbcContext();

		result.setMessage(message);
		result.setSkip(skip);
		result.setTrackProgressByUpdateCount(trackProgressByUpdateCount);
		result.setDataSource(dataSource);
		result.setSuppliers(suppliers);

		if (mode == SqlMode.SEQUENTIAL) {
			result.setMultithreaded(false);
			result.setThreads(1);
		} else if (mode == SqlMode.CONCURRENT) {
			result.setMultithreaded(true);
			if (env != null) {
				Integer threads = new Integer(SpringUtils.getProperty(env, THREAD_COUNT_KEY));
				result.setThreads(threads);
			} else {
				result.setThreads(DEFAULT_CONCURRENT_THREAD_COUNT);
			}
		}

		return result;
	}

	public static List<SqlSupplier> getSqlSuppliersByLocationsProperty(String locationsProperty, Environment env, Map<String, LocationSupplierSourceBean> extensionMappings) {
		LocationSuppliersFactoryBean factory = new LocationSuppliersFactoryBean();
		factory.setProperty(locationsProperty);
		factory.setEnv(env);
		factory.setExtensionMappings(extensionMappings);
		return new ArrayList<SqlSupplier>(factory.getObject());
	}

	public static String getPropertyPrefix(String groupKey, SqlMode sqlMode) {
		StringBuilder sb = new StringBuilder();
		sb.append(groupKey);
		sb.append(".");
		sb.append(sqlMode.toString().toLowerCase());

		return sb.toString();
	}

	public static JdbcContext buildJdbcContextFromGroupedSql(GroupedSqlConfig groupConfig) {

		String propertyPrefix = getPropertyPrefix(groupConfig.getGroupKey(), groupConfig.getSqlMode());

		List<SqlSupplier> suppliers = getSqlSuppliersByLocationsProperty(propertyPrefix, groupConfig.getEnvironment(), groupConfig.getJdbcCommonConfig().jdbcExtensionMappings());

		String message = SpringUtils.getProperty(groupConfig.getEnvironment(), propertyPrefix + MESSAGE_PROPERTY_SUFFIX);
		boolean skip = SpringUtils.getBoolean(groupConfig.getEnvironment(), propertyPrefix + SKIP_PROPERTY_SUFFIX, false);

		String trackProgressKey = propertyPrefix + TRACK_PROGRESS_PROPERTY_SUFFIX;
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(groupConfig.getEnvironment(), trackProgressKey, false);
		logger.debug("{}={}", trackProgressKey, trackProgressByUpdateCount);

		JdbcContext context = buildJdbcContext(message, skip, trackProgressByUpdateCount, groupConfig.getDataSourceConfig().jdbcDataSource(), suppliers, groupConfig.getSqlMode(),
				groupConfig.getEnvironment());

		if (groupConfig.getSqlListener() != null) {
			context.setListener(groupConfig.getSqlListener());
		} else {
			context.setListener(buildSummaryListener(groupConfig.getEnvironment()));
		}

		return context;
	}

	public static NotifyingListener buildSummaryListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener(false));
		list.add(buildLogListener(env));
		return new NotifyingListener(list);
	}

	public static NotifyingListener buildSummaryAndProgressListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		list.add(buildLogListener(env));
		return new NotifyingListener(list);
	}

	public static LogSqlListener buildLogListener(Environment env) {
		String level = SpringUtils.getProperty(env, SQL_LOG_LEVEL_KEY, LogSqlListener.DEFAULT_LOGGER_LEVEL.name());
		String mode = SpringUtils.getProperty(env, SQL_LOG_MODE_KEY, LogSqlListener.DEFAULT_MODE.name());

		LogSqlListener lsl = new LogSqlListener();
		lsl.setLevel(LoggerLevel.valueOf(level));
		lsl.setMode(LogSqlMode.valueOf(mode));
		return lsl;
	}

	public static DataSummaryListener getConcurrentDataSummaryListener(Environment env, String groupKey, SqlMode sqlMode) {
		String propertyPrefix = getPropertyPrefix(groupKey, sqlMode);
		String label = SpringUtils.getProperty(env, propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(env, propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		return dsl;
	}

}
