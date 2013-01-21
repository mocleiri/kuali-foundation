package org.kuali.common.jdbc.threads;

import org.kuali.common.jdbc.listener.ProgressListener;

public class ThreadsProgressListener extends ProgressListener {

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing here
		// this overrides the super class behavior that initializes the total SQL statement count
	}

}
