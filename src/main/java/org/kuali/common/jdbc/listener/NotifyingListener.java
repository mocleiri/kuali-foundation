package org.kuali.common.jdbc.listener;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlListener;
import org.kuali.common.jdbc.context.ExecutionContext;

/**
 * Don't actually do any work ourself, just notify our listeners so they can do they actual work.
 */
public class NotifyingListener implements SqlListener {

	List<SqlListener> listeners = new ArrayList<SqlListener>();

	@Override
	public void beforeMetaData(ExecutionContext context) {
		for (SqlListener listener : listeners) {
			listener.beforeMetaData(context);
		}
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		for (SqlListener listener : listeners) {
			listener.beforeExecution(event);
		}
	}

	@Override
	public void beforeExecuteSql(String sql) {
		for (SqlListener listener : listeners) {
			listener.beforeExecuteSql(sql);
		}
	}

	@Override
	public void afterExecuteSql(String sql) {
		for (SqlListener listener : listeners) {
			listener.afterExecuteSql(sql);
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		for (SqlListener listener : listeners) {
			listener.afterExecution(event);
		}
	}
}
