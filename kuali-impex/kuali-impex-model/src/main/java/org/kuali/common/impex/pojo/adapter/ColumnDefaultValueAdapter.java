package org.kuali.common.impex.pojo.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.impex.pojo.Column;

import com.google.common.base.Optional;

public class ColumnDefaultValueAdapter extends XmlAdapter<Column, Column> {

	@Override
	public final Column marshal(Column column) {
		return column;
	}

	@Override
	public final Column unmarshal(Column column) {
		if (column == null) {
			return null;
		} else {
			return getNewColumn(column);
		}
	}

	protected Column getNewColumn(Column column) {
		switch (column.getType()) {
		case BLOB:
		case CLOB:
			return column;
		case BIT:
		case DATE:
		case FLOAT:
		case INTEGER:
		case TIMESTAMP:
			return getTrimmedDefaultValue(column);
		case CHAR:
		case STRING:
			return getTrimmedStringDefaultValue(column);
		default:
			throw new IllegalArgumentException("Column type [" + column.getType() + "] is unknown");
		}
	}

	protected boolean trimmingWontChangeAnything(Column column) {
		Optional<String> defaultValue = column.getDefaultValue();
		if (!defaultValue.isPresent()) {
			return true;
		}
		String original = defaultValue.get();
		String trimmed = original.trim();
		return original.equals(trimmed);
	}

	protected Column getTrimmedDefaultValue(Column column) {
		if (trimmingWontChangeAnything(column)) {
			return column;
		} else {
			String trimmed = column.getDefaultValue().get().trim();
			return copyOf(column, trimmed);
		}
	}

	/**
	 * JDBC drivers are supposed to return string based default values enclosed in single quotes. This methods trims whitespace only after checking to make sure the trimmed value
	 * begins with and ends with a single quote.
	 */
	protected Column getTrimmedStringDefaultValue(Column column) {
		if (trimmingWontChangeAnything(column)) {
			return column;
		} else {
			String original = column.getDefaultValue().get();
			String trimmed = original.trim();
			if (trimmed.equals("'")) {
				return column;
			}
			if (trimmed.startsWith("'") && trimmed.endsWith("'")) {
				return copyOf(column, trimmed);
			} else {
				return column;
			}
		}
	}

	protected Column copyOf(Column column, String defaultValue) {
		return new Column.Builder(column.getName(), column.getType()).copyOf(column, defaultValue).build();
	}
}
