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

	private final String url;
	private final String username;
	private final String query;
	private final ImmutableList<Column> metadata;
	private final ImmutableTable<Integer, Integer, Optional<Object>> data;

	private ExecuteQueryResult(Builder builder) {
		this.url = builder.url;
		this.username = builder.username;
		this.query = builder.query;
		this.metadata = ImmutableList.copyOf(builder.metadata);
		this.data = ImmutableTable.copyOf(builder.data);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<ExecuteQueryResult> {

		private String url;
		private String username;
		private String query;
		private List<Column> metadata;
		private Table<Integer, Integer, Optional<Object>> data;

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withQuery(String query) {
			this.query = query;
			return this;
		}

		public Builder withMetadata(List<Column> metadata) {
			this.metadata = metadata;
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

	public List<Column> getMetadata() {
		return metadata;
	}

	public Table<Integer, Integer, Optional<Object>> getData() {
		return data;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

}
