package org.kuali.common.devops.table;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

public final class TableCellContext {

	private final int row;
	private final int column;
	private final List<String> tokens;

	private TableCellContext(Builder builder) {
		this.row = builder.row;
		this.column = builder.column;
		this.tokens = builder.tokens;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<TableCellContext> {

		private int row;
		private int column;
		private List<String> tokens;

		public Builder row(int row) {
			this.row = row;
			return this;
		}

		public Builder column(int column) {
			this.column = column;
			return this;
		}

		public Builder tokens(List<String> tokens) {
			this.tokens = tokens;
			return this;
		}

		@Override
		public TableCellContext build() {
			TableCellContext instance = new TableCellContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(TableCellContext instance) {
			checkArgument(instance.row >= 0, "'row' must be greater than or equal to zero");
			checkArgument(instance.column >= 0, "'column' must be greater than or equal to zero");
			checkNotNull(instance.tokens, "'tokens' cannot be null");
		}
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public List<String> getTokens() {
		return tokens;
	}

}
