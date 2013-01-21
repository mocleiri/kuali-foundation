package org.kuali.common.jdbc.threads;

import org.kuali.common.jdbc.listener.ProgressListener;

public class ThreadsProgressListener extends ProgressListener {

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing here to prevent this.total from being altered
	}

}
