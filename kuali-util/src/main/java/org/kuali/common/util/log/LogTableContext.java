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
package org.kuali.common.util.log;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;

public class LogTableContext {

	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.INFO;
	public static final boolean DEFAULT_LEFT_ALIGN = false;
	public static final Logger DEFAULT_LOGGER = LoggerUtils.LOGGER_UTILS_LOGGER;
	public static final String NO_TITLE = NullUtils.NONE;
	public static final String DEFAULT_TITLE = NO_TITLE;

	private final String title;
	private final List<String> columns;
	private final List<Object[]> rows;
	private final Logger logger;
	private final LoggerLevel level;
	private final boolean leftAlign;

	public LogTableContext(List<String> columns, List<Object[]> rows) {
		this(columns, rows, DEFAULT_LOGGER);
	}

	public LogTableContext(String title, List<String> columns, List<Object[]> rows) {
		this(title, columns, rows, DEFAULT_LOGGER_LEVEL, DEFAULT_LOGGER, DEFAULT_LEFT_ALIGN);
	}

	public LogTableContext(List<String> columns, List<Object[]> rows, Logger logger) {
		this(DEFAULT_TITLE, columns, rows, logger);
	}

	public LogTableContext(String title, List<String> columns, List<Object[]> rows, Logger logger) {
		this(title, columns, rows, DEFAULT_LOGGER_LEVEL, logger, DEFAULT_LEFT_ALIGN);

	}

	public LogTableContext(List<String> columns, List<Object[]> rows, LoggerLevel level, Logger logger, boolean leftAlign) {
		this(DEFAULT_TITLE, columns, rows, level, logger, leftAlign);
	}

	public LogTableContext(String title, List<String> columns, List<Object[]> rows, LoggerLevel level, Logger logger, boolean leftAlign) {
		Assert.noNulls(columns, rows, logger, level);
		Assert.noBlanks(title);
		this.title = title;
		this.columns = ListUtils.newImmutableArrayList(columns);
		this.rows = ListUtils.newImmutableArrayList(rows);
		this.level = level;
		this.logger = logger;
		this.leftAlign = leftAlign;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<Object[]> getRows() {
		return rows;
	}

	public Logger getLogger() {
		return logger;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public boolean isLeftAlign() {
		return leftAlign;
	}

}
