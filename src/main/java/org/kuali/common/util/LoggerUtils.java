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
package org.kuali.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * @deprecated
 */
@Deprecated
public class LoggerUtils {

	public static final Logger LOGGER_UTILS_LOGGER = LoggerFactory.getLogger(LoggerUtils.class);
	private static final Obscurer DEFAULT_OBSCURER = new DefaultObscurer();
	private static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	public static String getLogMsg(List<String> includes, List<String> excludes) {
		if (CollectionUtils.isEmpty(includes) && CollectionUtils.isEmpty(excludes)) {
			return "";
		}
		String includesCSV = StringUtils.trimToNull(CollectionUtils.getSpaceSeparatedCSV(includes));
		String excludesCSV = StringUtils.trimToNull(CollectionUtils.getSpaceSeparatedCSV(excludes));
		List<KeyValue> msgs = new ArrayList<KeyValue>();
		if (!StringUtils.isBlank(includesCSV)) {
			msgs.add(new KeyValue("includes", includesCSV));
		}
		if (!StringUtils.isBlank(excludesCSV)) {
			msgs.add(new KeyValue("excludes", excludesCSV));
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < msgs.size(); i++) {
			if (i != 0) {
				sb.append("  ");
			}
			KeyValue msg = msgs.get(i);
			sb.append(msg.getKey());
			sb.append(": ");
			sb.append(msg.getValue());
		}
		sb.append("]");
		return sb.toString();
	}

	public static String getLogMsg(StringFilter filter) {
		Assert.notNull(filter, "filter is null");
		return getLogMsg(filter.getIncludes(), filter.getExcludes());
	}

	public static Object[] getLogMsgArgs(StringFilter filter) {
		Assert.notNull(filter, "filter is null");
		String includes = CollectionUtils.getSpaceSeparatedCSV(filter.getIncludes());
		String excludes = CollectionUtils.getSpaceSeparatedCSV(filter.getExcludes());
		return new Object[] { includes, excludes };
	}

	public static void log(LogMsg msg, Logger logger) {
		Assert.notNull(msg.getLevel(), "level is null");
		Assert.notNull(logger, "logger is null");
		logMsg(msg.getMessage(), msg.getArgs(), logger, msg.getLevel());
	}

	public static int[] getPadding(List<String> columns, List<Object[]> argsList) {
		int[] padding = new int[columns.size()];
		for (int i = 0; i < padding.length; i++) {
			padding[i] = Math.max(padding[i], columns.get(i).length());
		}
		for (Object[] args : argsList) {
			Assert.isTrue(columns.size() == args.length, "Column count must equals args.length");
			for (int i = 0; i < args.length; i++) {
				padding[i] = Math.max(padding[i], args[i].toString().length());
			}
		}
		return padding;
	}

	public static String getHeader(List<String> columns, int[] padding, boolean leftAlign) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columns.size(); i++) {
			if (i == 0) {
				sb.append("||  ");
			} else {
				sb.append("|  ");
			}
			if (leftAlign) {
				sb.append(StringUtils.rightPad(columns.get(i), padding[i]));
			} else {
				sb.append(StringUtils.leftPad(columns.get(i), padding[i]));
			}
			if (i == columns.size() - 1) {
				sb.append("  ||");
			} else {
				sb.append("  |");
			}
		}
		return sb.toString();
	}

	public static void updateArgsList(List<Object[]> argsList, int[] padding, boolean leftAlign) {
		for (Object[] args : argsList) {
			for (int i = 0; i < args.length; i++) {
				if (leftAlign) {
					args[i] = StringUtils.rightPad(args[i].toString(), padding[i]);
				} else {
					args[i] = StringUtils.leftPad(args[i].toString(), padding[i]);
				}
			}
		}
	}

	public static void logTable(List<String> columns, List<Object[]> rows, LoggerLevel level, Logger logger) {
		logTable(columns, rows, level, logger, false);
	}

	public static void logTable(List<String> columns, List<Object[]> rows, LoggerLevel level, Logger logger, boolean leftAlign) {
		LogTableContext context = new LogTableContext();
		context.setColumns(columns);
		context.setRows(rows);
		context.setLevel(level);
		context.setLogger(logger);
		context.setLeftAlign(leftAlign);

		logTable(context);
	}

	public static void logTable(List<String> columns, List<Object[]> rows) {
		LogTableContext context = new LogTableContext(columns, rows);
		logTable(context);
	}

	public static String getTable(LogTableContext context) {
		Assert.notNull(context, "context is null");
		Assert.notNull(context.getColumns(), "columns is null");
		Assert.notNull(context.getRows(), "rows is null");
		int[] padding = getPadding(context.getColumns(), context.getRows());
		int cols = context.getColumns().size();
		int rows = context.getRows().size();

		String header = getHeader(context.getColumns(), padding, context.isLeftAlign());
		updateArgsList(context.getRows(), padding, context.isLeftAlign());
		Properties properties = getProperties(context.getRows());
		String tableString = getTableString(rows, cols);

		String resolved = HELPER.replacePlaceholders(tableString, properties);

		return header + "\n" + resolved;
	}

	public static void logTable(LogTableContext context) {

		String table = getTable(context);

		Assert.notNull(context.getLogger(), "logger is null");
		Assert.notNull(context.getLevel(), "level is null");

		String msg = context.getTitle() + "\n\n" + table;

		logMsg(msg, context.getLogger(), context.getLevel());

	}

	protected static String getTableString(int rows, int cols) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				sb.append("${" + getPropertyKey(row, col) + "}");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	protected static Properties getProperties(List<Object[]> rows) {
		Properties properties = new Properties();
		for (int row = 0; row < rows.size(); row++) {
			Object[] rowData = rows.get(row);
			for (int col = 0; col < rowData.length; col++) {
				String key = getPropertyKey(row, col);
				StringBuilder sb = new StringBuilder();
				if (col == 0) {
					sb.append("||  ");
				} else {
					sb.append("|  ");
				}
				sb.append(rowData[col] + "");
				if (col == rowData.length - 1) {
					sb.append("  ||");
				} else {
					sb.append("  |");
				}
				properties.setProperty(key, sb.toString());
			}
		}
		return properties;
	}

	protected static String getPropertyKey(int row, int col) {
		return "log.table.row." + row + ".col." + col;
	}

	public static void logLines(String s, Logger logger, LoggerLevel level) {
		if (s == null) {
			return;
		}
		String[] lines = StringUtils.split(s, "\n");
		for (String line : lines) {
			LoggerUtils.logMsg(line, logger, level);
		}
	}

	public static final void logMsg(String msg, Object[] args, Logger logger, LoggerLevel level) {
		switch (level) {
		case DEBUG:
			logger.debug(msg, args);
			return;
		case TRACE:
			logger.trace(msg, args);
			return;
		case INFO:
			logger.info(msg, args);
			return;
		case WARN:
			logger.warn(msg, args);
			return;
		case ERROR:
			logger.error(msg, args);
			return;
		default:
			throw new IllegalArgumentException("Logger level " + level + " is unknown");
		}
	}

	public static final void logMsg(String msg, Logger logger, LoggerLevel level) {
		logMsg(msg, null, logger, level);
	}

	public static final String getUsername(String username) {
		return getNullAsNone(username);
	}

	public static final String getNullAsNone(String string) {
		if (string == null) {
			return NullUtils.NONE;
		} else {
			return string;
		}
	}

	public static final String getPassword(String username, String password) {
		return getPassword(username, password, DEFAULT_OBSCURER);
	}

	public static boolean isNullOrNone(String s) {
		return NullUtils.isNullOrNone(s);
	}

	public static final String getPassword(String username, String password, Obscurer obscurer) {
		if (isNullOrNone(password)) {
			// There is no password, return NONE
			return NullUtils.NONE;
		} else if (StringUtils.equals(username, password)) {
			// Not exactly high security, display the clear text value
			return password;
		} else {
			// Otherwise obscure it
			return obscurer.obscure(password);
		}
	}

}
