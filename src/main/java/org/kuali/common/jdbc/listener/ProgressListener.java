package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.threads.listener.PercentCompleteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressListener extends PercentCompleteListener<String> implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(ProgressListener.class);

	long count = 0;
	long total = 0;

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// The total number of SQL statements being executed
		this.total = JdbcUtils.getSqlCount(event.getSources());
	}

	@Override
	public void beforeExecuteSql(String sql) {
	}

	@Override
	public void afterExecuteSql(String sql) {
		// The first SQL statement was just executed
		if (count == 0) {
			logger.debug("Progress started");
			progressStarted();
		}

		// Increment the counter
		this.count++;

		// TODO This breaks for clients trying to track execution progress of more than Integer.MAX_VALUE SQL statements
		progressOccurred((int) count, (int) total, null);

		// The last SQL statement was just executed
		if (count == total) {
			logger.debug("Progress complete");
			progressCompleted();
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}

}
