package org.kuali.common.devops.logic;

import static java.lang.Integer.valueOf;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;

public class Tables {

	public static <T> void addRow(Table<Integer, Integer, T> table, T... elements) {
		addRow(table, ImmutableList.copyOf(elements));
	}

	public static <T> void addRow(Table<Integer, Integer, T> table, List<T> elements) {
		int row = table.rowKeySet().size();
		for (int col = 0; col < elements.size(); col++) {
			table.put(valueOf(row), valueOf(col), elements.get(col));
		}
	}
}
