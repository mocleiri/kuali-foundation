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
package org.springframework.jdbc.datasource;

import java.util.List;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

@IdiotProofImmutable
public final class ExecuteQueryResult {

	private final Database database;
	private final String query;
	private final ImmutableList<Column> columns;
	private final ImmutableTable<Integer, Integer, Optional<Object>> data;

	private ExecuteQueryResult(Builder builder) {
		this.database = builder.database;
		this.query = builder.query;
		this.columns = ImmutableList.copyOf(builder.columns);
		this.data = ImmutableTable.copyOf(builder.data);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<ExecuteQueryResult> {

		private Database database;
		private String query;
		private List<Column> columns;
		private Table<Integer, Integer, Optional<Object>> data;

		public Builder withDatabase(Database database) {
			this.database = database;
			return this;
		}

		public Builder withColumns(List<Column> columns) {
			this.columns = columns;
			return this;
		}

		public Builder withData(Table<Integer, Integer, Optional<Object>> data) {
			this.data = data;
			return this;
		}

		@Override
		public ExecuteQueryResult build() {
			return validate(new ExecuteQueryResult(this));
		}
	}

	public String getQuery() {
		return query;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Table<Integer, Integer, Optional<Object>> getData() {
		return data;
	}

	public Database getDatabase() {
		return database;
	}

}
