package org.springframework.jdbc.datasource;

import java.util.Comparator;

public enum ColumnNameComparator implements Comparator<Column> {
	INSTANCE;

	@Override
	public int compare(Column one, Column two) {
		return one.getName().compareTo(two.getName());
	}

}
