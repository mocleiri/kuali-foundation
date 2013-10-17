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
package org.kuali.common.jdbc.listeners;

import org.kuali.common.jdbc.model.LogSqlMode;
import org.kuali.common.jdbc.model.event.SqlEvent;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogSqlListener extends NoOpSqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.DEBUG;
	public static final LogSqlMode DEFAULT_MODE = LogSqlMode.AFTER;
	public static final boolean DEFAULT_FLATTEN = true;

	private final LoggerLevel level;
	private final boolean flatten;
	private final LogSqlMode mode;

	public LogSqlListener() {
		this(DEFAULT_LOGGER_LEVEL, DEFAULT_MODE, DEFAULT_FLATTEN);
	}

	public LogSqlListener(LoggerLevel level, LogSqlMode mode) {
		this(level, mode, DEFAULT_FLATTEN);
	}

	public LogSqlListener(LoggerLevel level, LogSqlMode mode, boolean flatten) {
		Assert.noNulls(level, mode);
		this.level = level;
		this.mode = mode;
		this.flatten = flatten;
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

	public static Logger getLogger() {
		return logger;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public boolean isFlatten() {
		return flatten;
	}

	public LogSqlMode getMode() {
		return mode;
	}

}
