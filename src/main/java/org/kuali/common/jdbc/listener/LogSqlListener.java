package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlListener;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSqlListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	LoggerLevel level = LoggerLevel.INFO;

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void beforeExecuteSql(String sql) {
		LoggerUtils.logMsg(sql, logger, level);
	}

	@Override
	public void afterExecuteSql(String sql) {
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
