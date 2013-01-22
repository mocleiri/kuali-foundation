package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	LoggerLevel level = LoggerLevel.INFO;
	String beforeMetaData;
	String beforeExecution;
	String bucketsCreated;
	String beforeExecuteSql;
	String afterExecuteSql;
	String afterExecution;

	@Override
	public void beforeMetaData(ExecutionContext context) {
		LoggerUtils.logMsg(beforeMetaData, logger, level);
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		LoggerUtils.logMsg(beforeExecution, logger, level);
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
		LoggerUtils.logMsg(bucketsCreated, logger, level);
	}

	@Override
	public void beforeExecuteSql(String sql) {
		LoggerUtils.logMsg(beforeExecuteSql, logger, level);
	}

	@Override
	public void afterExecuteSql(String sql) {
		LoggerUtils.logMsg(afterExecuteSql, logger, level);
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		LoggerUtils.logMsg(afterExecution, logger, level);
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
