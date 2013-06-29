package org.kuali.common.jdbc;

import java.util.Comparator;

public class TableStatsSizeComparator implements Comparator<TableStats> {

	@Override
	public int compare(TableStats o1, TableStats o2) {
		if (o1.getSize() < o2.getSize()) {
			return -1;
		} else if (o1.getSize() > o2.getSize()) {
			return 1;
		} else {
			return 0;
		}
	}

}
