package org.kuali.common.jdbc.context;

import java.util.List;

import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.jdbc.listener.NoOpSqlListener;
import org.kuali.common.jdbc.listener.SqlListener;

public class ExecutionContext {

	// If false, no SQL is executed.
	// Everything leading up to SQL execution still takes place
	// Connecting to the database, parsing SQL, etc.
	boolean execute = true;

	// Use this to enable multi-threaded SQL execution
	// When used, SQL supplied to this context does not execute sequentially
	int threads = 1;
	SqlListener listener = new NoOpSqlListener();
	JdbcContext jdbcContext;
	SqlReader reader;
	List<String> locations;
	String encoding;
	List<String> sql;
	String message;

	public JdbcContext getJdbcContext() {
		return jdbcContext;
	}

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List<String> getSql() {
		return sql;
	}

	public void setSql(List<String> sql) {
		this.sql = sql;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public SqlListener getListener() {
		return listener;
	}

	public void setListener(SqlListener listener) {
		this.listener = listener;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
