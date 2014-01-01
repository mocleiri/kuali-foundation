/**
 * Copyright 2010-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.context;

import java.util.List;

import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.jdbc.listener.NoOpSqlListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;

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
	String encoding;
	List<String> locations;
	List<String> sql;
	List<SqlSupplier> suppliers;
	String message;

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
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

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getSql() {
		return sql;
	}

	public void setSql(List<String> sql) {
		this.sql = sql;
	}

	public List<SqlSupplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<SqlSupplier> suppliers) {
		this.suppliers = suppliers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
