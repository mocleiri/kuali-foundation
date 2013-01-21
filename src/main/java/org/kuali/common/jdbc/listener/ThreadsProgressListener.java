package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlSource;

public class ThreadsProgressListener extends ProgressListener {

	List<SqlSource> sources;

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		// intentionally do nothing to override the super class behavior
	}

	public List<SqlSource> getSources() {
		return sources;
	}

	public void setSources(List<SqlSource> sources) {
		this.sources = sources;
	}

}
