package org.kuali.common.util;

import java.util.List;

import org.slf4j.Logger;

public class LogTableContext {

	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.INFO;
	public static final boolean DEFAULT_LEFT_ALIGN = false;

	LoggerLevel level = DEFAULT_LOGGER_LEVEL;
	boolean leftAlign = DEFAULT_LEFT_ALIGN;
	Logger logger = LoggerUtils.LOGGER_UTILS_LOGGER;

	List<String> columns;
	List<Object[]> rows;

	public LogTableContext() {
		this(null, null);
	}

	public LogTableContext(List<String> columns, List<Object[]> rows) {
		super();
		this.columns = columns;
		this.rows = rows;
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

}
