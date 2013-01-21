package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.context.ExecutionContext;

public interface SqlListener {

	void beforeMetaData(ExecutionContext context);

	void beforeExecution(SqlExecutionEvent event);

	void beforeExecuteSql(String sql);

	void afterExecuteSql(String sql);

	void afterExecution(SqlExecutionEvent event);

}
