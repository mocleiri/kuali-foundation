/**
 * Copyright 2010-2013 The Kuali Foundation
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

import javax.sql.DataSource;

import org.kuali.common.jdbc.CommitMode;
import org.kuali.common.jdbc.listener.NoOpSqlListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;

/**
 * @deprecated
 */
@Deprecated
public class JdbcContext {

	// If true, no SQL is executed.
	// Everything leading up to SQL execution still takes place
	// Connecting to the database, parsing SQL, etc.
	boolean skip;

	// Use this to enable multi-threaded SQL execution
	// When used, SQL supplied to this context does not execute sequentially
	int threads = 1;
	boolean multithreaded;
	SqlListener listener = new NoOpSqlListener();
	CommitMode commitMode = CommitMode.PER_SUPPLIER;
	DataSource dataSource;
	List<SqlSupplier> suppliers;
	String message;
	boolean skipMetaData;
	boolean trackProgressByUpdateCount;

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
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

	public CommitMode getCommitMode() {
		return commitMode;
	}

	public void setCommitMode(CommitMode commitMode) {
		this.commitMode = commitMode;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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

	public boolean isMultithreaded() {
		return multithreaded;
	}

	public void setMultithreaded(boolean multithreaded) {
		this.multithreaded = multithreaded;
	}

	public boolean isSkipMetaData() {
		return skipMetaData;
	}

	public void setSkipMetaData(boolean skipMetaData) {
		this.skipMetaData = skipMetaData;
	}

	public boolean isTrackProgressByUpdateCount() {
		return trackProgressByUpdateCount;
	}

	public void setTrackProgressByUpdateCount(boolean trackProgressByUpdateCount) {
		this.trackProgressByUpdateCount = trackProgressByUpdateCount;
	}

}
