package org.kuali.common.jdbc.threads;

import org.kuali.common.jdbc.listener.ProgressListener;

public class ThreadsProgressListener extends ProgressListener {

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing here
		// this prevents super.beforeExecution() from firing
		// clients using this class initialize the total SQL statement count on their own
	}

}
