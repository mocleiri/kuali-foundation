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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;

public class LoggerUtils {

	private static final Obscurer DEFAULT_OBSCURER = new DefaultObscurer();

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
			if (i != 0) {
				sb.append("  ");
			}
			if (leftAlign) {
				sb.append(StringUtils.rightPad(columns.get(i), padding[i]));
			} else {
				sb.append(StringUtils.leftPad(columns.get(i), padding[i]));
			}
		}
		return sb.toString();
	}

	public static String getMsg(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (i != 0) {
				sb.append("  ");
			}
			sb.append("{}");
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
		int[] padding = getPadding(columns, rows);
		logMsg(getHeader(columns, padding, leftAlign), logger, level);
		String msg = getMsg(padding.length);
		updateArgsList(rows, padding, leftAlign);
		for (Object[] args : rows) {
			logMsg(msg, args, logger, level);
		}
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
			return Constants.NONE;
		} else {
			return string;
		}
	}

	public static final String getPassword(String username, String password) {
		return getPassword(username, password, DEFAULT_OBSCURER);
	}

	public static boolean isNullOrNone(String s) {
		if (s == null) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(Constants.NONE, s)) {
			return true;
		}
		return StringUtils.equalsIgnoreCase(Constants.NULL, s);
	}

	public static final String getPassword(String username, String password, Obscurer obscurer) {
		if (isNullOrNone(password)) {
			// There is no password, return NONE
			return Constants.NONE;
		} else if (StringUtils.equals(username, password)) {
			// Not exactly high security, display the clear text value
			return password;
		} else {
			// Otherwise obscure it
			return obscurer.obscure(password);
		}
	}

}
