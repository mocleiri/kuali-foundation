package org.kuali.common.devops.logic;

import com.google.common.collect.Table;

public class Tables {

	public static void addRow(Table<Integer, Integer, Object> table, Object... objects) {
		int row = table.rowKeySet().size();
		for (int col = 0; col < objects.length; col++) {
			table.put(Integer.valueOf(row), Integer.valueOf(col), objects[col]);
		}
	}

	public static void addRow(Table<Integer, Integer, String> table, String... strings) {
		int row = table.rowKeySet().size();
		for (int col = 0; col < strings.length; col++) {
			table.put(Integer.valueOf(row), Integer.valueOf(col), strings[col]);
		}
	}
}
