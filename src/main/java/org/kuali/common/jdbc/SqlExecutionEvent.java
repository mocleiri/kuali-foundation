package org.kuali.common.jdbc;

import java.util.List;

import org.kuali.common.jdbc.context.ExecutionContext;

public class SqlExecutionEvent {

	ExecutionContext context;
	List<SqlSource> sources;

	public SqlExecutionEvent() {
		this(null, null);
	}

	public SqlExecutionEvent(ExecutionContext context, List<SqlSource> sources) {
		super();
		this.context = context;
		this.sources = sources;
	}

	public ExecutionContext getContext() {
		return context;
	}

	public void setContext(ExecutionContext context) {
		this.context = context;
	}

	public List<SqlSource> getSources() {
		return sources;
	}

	public void setSources(List<SqlSource> sources) {
		this.sources = sources;
	}

}
