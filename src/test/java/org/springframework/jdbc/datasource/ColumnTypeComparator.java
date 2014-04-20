package org.springframework.jdbc.datasource;

import java.util.Comparator;

public enum ColumnTypeComparator implements Comparator<Column> {
	INSTANCE;

	@Override
	public int compare(Column one, Column two) {
		int comparison = one.getType().getCanonicalName().compareTo(two.getType().getCanonicalName());
		if (comparison == 0) {
			return ColumnNameComparator.INSTANCE.compare(one, two);
		} else {
			return comparison;
		}
	}

}
