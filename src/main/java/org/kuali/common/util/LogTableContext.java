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

import org.slf4j.Logger;

/**
 * @deprecated
 */
@Deprecated
public class LogTableContext {

	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.INFO;
	public static final boolean DEFAULT_LEFT_ALIGN = false;
	public static final Logger DEFAULT_LOGGER = LoggerUtils.LOGGER_UTILS_LOGGER;
	public static final String DEFAULT_TITLE = "Displaying Table";

	LoggerLevel level = DEFAULT_LOGGER_LEVEL;
	boolean leftAlign = DEFAULT_LEFT_ALIGN;
	Logger logger = DEFAULT_LOGGER;

	String title = DEFAULT_TITLE;
	List<String> columns;
	List<Object[]> rows;

	public LogTableContext() {
		this(null, null);
	}

	public LogTableContext(List<String> columns, List<Object[]> rows) {
		this(columns, rows, DEFAULT_LOGGER);
	}

	public LogTableContext(List<String> columns, List<Object[]> rows, Logger logger) {
		this(null, columns, rows, logger);
	}

	public LogTableContext(String title, List<String> columns, List<Object[]> rows, Logger logger) {
		super();
		this.title = title;
		this.columns = columns;
		this.rows = rows;
		this.logger = logger;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

	public boolean isLeftAlign() {
		return leftAlign;
	}

	public void setLeftAlign(boolean leftAlign) {
		this.leftAlign = leftAlign;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<Object[]> getRows() {
		return rows;
	}

	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
