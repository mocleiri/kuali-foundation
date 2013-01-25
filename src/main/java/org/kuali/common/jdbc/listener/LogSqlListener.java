package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSqlListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	LoggerLevel level = LoggerLevel.DEBUG;
	boolean flatten = true;

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
	}

	@Override
	public void beforeExecuteSql(String sql) {
		String msg = sql;
		if (flatten) {
			msg = "[" + Str.flatten(sql) + "]";
		}
		LoggerUtils.logMsg(msg, logger, level);
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

	public boolean isFlatten() {
		return flatten;
	}

	public void setFlatten(boolean flatten) {
		this.flatten = flatten;
	}

}
