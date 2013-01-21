package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.threads.SqlExecutionEvent;

public class ThreadsProgressListener extends ProgressListener {

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing here to prevent this.total from being altered
	}

}
