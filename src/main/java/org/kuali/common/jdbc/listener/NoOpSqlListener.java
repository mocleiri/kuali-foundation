package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.context.ExecutionContext;

public class NoOpSqlListener implements SqlListener {

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void beforeExecuteSql(String sql) {
	}

	@Override
	public void afterExecuteSql(String sql) {
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}
}
