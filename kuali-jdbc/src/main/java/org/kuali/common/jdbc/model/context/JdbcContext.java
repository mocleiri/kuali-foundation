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
package org.kuali.common.jdbc.model.context;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.listen.NoOpSqlListener;
import org.kuali.common.jdbc.listen.SqlListener;
import org.kuali.common.jdbc.model.enums.CommitMode;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class JdbcContext {

	public JdbcContext(DataSource dataSource, SqlSupplier supplier, String message) {
		this(dataSource, CollectionUtils.singletonList(supplier), message);
	}

	public JdbcContext(DataSource dataSource, List<SqlSupplier> suppliers, String message) {
		this(DEFAULT_SKIP, dataSource, suppliers, DEFAULT_THREADS, DEFAULT_MULTITHREADED, DEFAULT_LISTENER, DEFAULT_COMMIT_MODE, message, DEFAULT_TRACK_PROGRESS_BY_UPDATE_COUNT);
	}

	public JdbcContext(boolean skip, DataSource dataSource, List<SqlSupplier> suppliers, int threads, SqlListener listener, CommitMode commitMode) {
		this(skip, dataSource, suppliers, threads, DEFAULT_MULTITHREADED, listener, commitMode, null, DEFAULT_TRACK_PROGRESS_BY_UPDATE_COUNT);
	}

	public JdbcContext(boolean skip, DataSource dataSource, List<SqlSupplier> suppliers, int threads, boolean multithreaded, SqlListener listener, CommitMode commitMode,
			String message, boolean trackProgressByUpdateCount) {
		Assert.noNulls(listener, commitMode, dataSource, suppliers);
		this.skip = skip;
		this.threads = threads;
		this.multithreaded = multithreaded;
		this.listener = listener;
		this.commitMode = commitMode;
		this.dataSource = dataSource;
		this.suppliers = CollectionUtils.unmodifiableCopy(suppliers);
		this.message = message;
		this.trackProgressByUpdateCount = trackProgressByUpdateCount;
	}

	public static final boolean DEFAULT_SKIP = false;
	public static final int DEFAULT_THREADS = 1;
	public static final boolean DEFAULT_MULTITHREADED = false;
	public static final boolean DEFAULT_TRACK_PROGRESS_BY_UPDATE_COUNT = false;
	public static final SqlListener DEFAULT_LISTENER = NoOpSqlListener.INSTANCE;
	public static final CommitMode DEFAULT_COMMIT_MODE = CommitMode.PER_SUPPLIER;

	// If true, no SQL is executed.
	// Everything leading up to SQL execution still takes place
	// Connecting to the database, parsing SQL, etc.
	private final boolean skip;

	// Use this to enable multi-threaded SQL execution
	// When used, SQL supplied to this context does not execute sequentially
	private final int threads;
	private final boolean multithreaded;
	private final SqlListener listener;
	private final CommitMode commitMode;
	private final DataSource dataSource;
	private final List<SqlSupplier> suppliers;
	private final String message;
	private final boolean trackProgressByUpdateCount;

	public boolean isSkip() {
		return skip;
	}

	public int getThreads() {
		return threads;
	}

	public boolean isMultithreaded() {
		return multithreaded;
	}

	public SqlListener getListener() {
		return listener;
	}

	public CommitMode getCommitMode() {
		return commitMode;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public List<SqlSupplier> getSuppliers() {
		return suppliers;
	}

	public String getMessage() {
		return message;
	}

	public boolean isTrackProgressByUpdateCount() {
		return trackProgressByUpdateCount;
	}

}
