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

import org.kuali.common.jdbc.listeners.LogSqlListener;
import org.kuali.common.jdbc.listeners.SqlListener;
import org.kuali.common.jdbc.model.enums.CommitMode;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.util.Assert;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class JdbcContext {

	private final DataSource dataSource;
	private final List<SqlSupplier> suppliers;

	// If true, no SQL is executed.
	// Everything leading up to SQL execution still takes place
	// Connecting to the database, parsing SQL, etc.
	private final boolean skipSqlExecution;

	private final int threads;
	// Use this to enable multi-threaded SQL execution
	// When true, SQL from each supplier is executed sequentially, but the suppliers are not executed sequentially.
	// The suppliers are split up evenly across the threads and executed concurrently
	private final boolean multithreaded;
	private final SqlListener listener;
	private final CommitMode commitMode;
	private final Optional<String> message;
	private final boolean trackProgressByUpdateCount;

	public boolean isSkipSqlExecution() {
		return skipSqlExecution;
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

	public Optional<String> getMessage() {
		return message;
	}

	public boolean isTrackProgressByUpdateCount() {
		return trackProgressByUpdateCount;
	}

	public static class Builder {

		// Required
		private final DataSource dataSource;
		private final List<SqlSupplier> suppliers;

		// Optional
		private boolean skipSqlExecution = false;
		private int threads = SqlContext.DEFAULT_THREADS;
		private boolean multithreaded = false;
		private SqlListener listener = new LogSqlListener();
		private CommitMode commitMode = CommitMode.PER_SUPPLIER;
		private Optional<String> message = Optional.absent();
		private boolean trackProgressByUpdateCount = false;

		public JdbcContext build() {
			Assert.noNulls(dataSource, suppliers, listener, commitMode, message);
			if (multithreaded) {
				Assert.isTrue(threads > 0, "threads must be a positive integer");
			}
			return new JdbcContext(this);
		}

		public Builder(DataSource dataSource, SqlSupplier supplier) {
			this(dataSource, ImmutableList.of(supplier));
		}

		public Builder(DataSource dataSource, List<SqlSupplier> suppliers) {
			this.dataSource = dataSource;
			this.suppliers = suppliers;
		}

		public Builder skipSqlExecution(boolean skipSqlExecution) {
			this.skipSqlExecution = skipSqlExecution;
			return this;
		}

		public Builder multithreaded(boolean multithreaded) {
			this.multithreaded = multithreaded;
			return this;
		}

		public Builder listener(SqlListener listener) {
			this.listener = listener;
			return this;
		}

		public Builder commitMode(CommitMode commitMode) {
			this.commitMode = commitMode;
			return this;
		}

		public Builder trackProgressByUpdateCount(boolean trackProgressByUpdateCount) {
			this.trackProgressByUpdateCount = trackProgressByUpdateCount;
			return this;
		}

		public Builder message(String message) {
			this.message = Optional.fromNullable(message);
			return this;
		}

		public Builder threads(int threads) {
			this.threads = threads;
			return this;
		}

	}

	private JdbcContext(Builder builder) {
		this.dataSource = builder.dataSource;
		this.suppliers = builder.suppliers;
		this.skipSqlExecution = builder.skipSqlExecution;
		this.threads = builder.threads;
		this.multithreaded = builder.multithreaded;
		this.listener = builder.listener;
		this.commitMode = builder.commitMode;
		this.message = builder.message;
		this.trackProgressByUpdateCount = builder.trackProgressByUpdateCount;
	}

}
