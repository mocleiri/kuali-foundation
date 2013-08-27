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
package org.kuali.common.jdbc.listener;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class LogSqlListener extends NoOpSqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.TRACE;
	public static final LogSqlMode DEFAULT_MODE = LogSqlMode.AFTER;

	LoggerLevel level = DEFAULT_LOGGER_LEVEL;
	boolean flatten = true;
	LogSqlMode mode = DEFAULT_MODE;

	public LogSqlListener() {
		this(DEFAULT_LOGGER_LEVEL, DEFAULT_MODE);
	}

	public LogSqlListener(LoggerLevel level, LogSqlMode mode) {
		super();
		this.level = level;
		this.mode = mode;
	}

	@Override
	public void beforeExecuteSql(SqlEvent event) {
		switch (mode) {
		case BEFORE:
		case BOTH:
			String sql = getSql(event.getSql(), flatten);
			LoggerUtils.logMsg(sql, logger, level);
			return;
		case AFTER:
			return;
		default:
			throw new IllegalArgumentException("Mode [" + mode + "] is unknown");
		}
	}

	@Override
	public void afterExecuteSql(SqlEvent event) {
		switch (mode) {
		case BEFORE:
			return;
		case BOTH:
		case AFTER:
			String sql = getSql(event.getSql(), flatten);
			String elapsed = FormatUtils.getTime(event.getStopTimeMillis() - event.getStartTimeMillis());
			Object[] args = { sql, elapsed };
			LoggerUtils.logMsg("{} - {}", args, logger, level);
			return;
		default:
			throw new IllegalArgumentException("Mode [" + mode + "] is unknown");
		}
	}

	protected String getSql(String sql, boolean flatten) {
		if (flatten) {
			return "[" + Str.flatten(sql) + "]";
		} else {
			return sql;
		}
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

	public boolean isFlatten() {
		return flatten;
	}

	public void setFlatten(boolean flatten) {
		this.flatten = flatten;
	}

	public LogSqlMode getMode() {
		return mode;
	}

	public void setMode(LogSqlMode mode) {
		this.mode = mode;
	}

}
