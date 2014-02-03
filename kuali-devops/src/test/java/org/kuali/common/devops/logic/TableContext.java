package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.spring.format.CsvStringFormatter;

public final class TableContext {

	private final int rows;
	private final int columns;
	private final List<String> headerTokens;
	private final CsvStringFormatter formatter;
	private final Map<String, Field> fieldNames;

	private TableContext(Builder builder) {
		this.rows = builder.rows;
		this.columns = builder.columns;
		this.headerTokens = builder.headerTokens;
		this.formatter = builder.formatter;
		this.fieldNames = builder.fieldNames;
	}

	public static class Builder {

		private int rows;
		private int columns;
		private List<String> headerTokens;
		private CsvStringFormatter formatter;
		private Map<String, Field> fieldNames;

		public Builder rows(int rows) {
			this.rows = rows;
			return this;
		}

		public Builder columns(int columns) {
			this.columns = columns;
			return this;
		}

		public Builder headerTokens(List<String> headerTokens) {
			this.headerTokens = headerTokens;
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

		public TableContext build() {
			TableContext instance = new TableContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(TableContext instance) {
			checkNotNull(instance.headerTokens, "'headerTokens' cannot be null");
			checkNotNull(instance.formatter, "'formatter' cannot be null");
			checkNotNull(instance.fieldNames, "'fieldNames' cannot be null");
		}
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public List<String> getHeaderTokens() {
		return headerTokens;
	}

	public CsvStringFormatter getFormatter() {
		return formatter;
	}

	public Map<String, Field> getFieldNames() {
		return fieldNames;
	}

}
