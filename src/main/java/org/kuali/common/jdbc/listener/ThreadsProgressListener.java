package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.SqlExecutionEvent;

public class ThreadsProgressListener extends ProgressListener {

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing to override the super class behavior
	}

}
