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
	private final ImmutableList<ColumnMetadata> metadata;
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
		private List<ColumnMetadata> metadata;
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

		public Builder withMetadata(List<ColumnMetadata> metadata) {
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

	public List<ColumnMetadata> getMetadata() {
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
