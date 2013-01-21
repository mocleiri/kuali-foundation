package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadsProgressListener extends ProgressListener {

	private static final Logger logger = LoggerFactory.getLogger(ThreadsProgressListener.class);

	List<SqlSource> sources;

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing to override the super class behavior
	}

	@Override
	public synchronized void afterExecuteSql(String sql) {
		if (count == 0) {
			logger.debug("Progress started");
			progressStarted();
		}
		// TODO This breaks for clients trying to track execution progress of more than Integer.MAX_VALUE SQL statements
		progressOccurred((int) count++, (int) total, null);
		if (count == total) {
			logger.debug("Progress complete");
			progressCompleted();
		}
	}

	public List<SqlSource> getSources() {
		return sources;
	}

	public void setSources(List<SqlSource> sources) {
		this.sources = sources;
	}

}
