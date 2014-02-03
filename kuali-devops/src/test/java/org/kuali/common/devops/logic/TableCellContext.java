package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.spring.format.CsvStringFormatter;

public final class TableCellContext {

	private final int row;
	private final int column;
	private final List<String> headerTokens;
	private final List<String> tokens;
	private final CsvStringFormatter formatter;
	private final Map<String, Field> fieldNames;

	private TableCellContext(Builder builder) {
		this.row = builder.row;
		this.column = builder.column;
		this.headerTokens = builder.headerTokens;
		this.tokens = builder.tokens;
		this.formatter = builder.formatter;
		this.fieldNames = builder.fieldNames;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<TableCellContext> {

		private int row;
		private int column;
		private List<String> headerTokens;
		private List<String> tokens;
		private CsvStringFormatter formatter;
		private Map<String, Field> fieldNames;

		public Builder row(int row) {
			this.row = row;
			return this;
		}

		public Builder column(int column) {
			this.column = column;
			return this;
		}

		public Builder headerTokens(List<String> headerTokens) {
			this.headerTokens = headerTokens;
			return this;
		}

		public Builder tokens(List<String> tokens) {
			this.tokens = tokens;
			return this;
		}

		public Builder formatter(CsvStringFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public Builder fieldNames(Map<String, Field> fieldNames) {
			this.fieldNames = fieldNames;
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
			checkNotNull(instance.headerTokens, "'headerTokens' cannot be null");
			checkNotNull(instance.tokens, "'tokens' cannot be null");
			checkNotNull(instance.formatter, "'formatter' cannot be null");
			checkNotNull(instance.fieldNames, "'fieldNames' cannot be null");
		}
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public List<String> getHeaderTokens() {
		return headerTokens;
	}

	public List<String> getTokens() {
		return tokens;
	}

	public CsvStringFormatter getFormatter() {
		return formatter;
	}

	public Map<String, Field> getFieldNames() {
		return fieldNames;
	}

}
