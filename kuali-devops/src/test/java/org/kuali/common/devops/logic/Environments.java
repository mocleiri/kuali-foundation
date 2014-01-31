package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;
import java.util.SortedSet;

import org.kuali.common.devops.model.Environment;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Environments {

	public static Table<Integer, Integer, String> getTable(List<Environment> envs) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < envs.size(); i++) {
			Environment env = envs.get(i);
			Integer row = i;
			table.put(row, new Integer(1), env.getId());
			table.put(row, new Integer(2), env.getFqdn());
			table.put(row, new Integer(3), env.getJava());
			table.put(row, new Integer(4), env.getType());
		}
		return table;
	}

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, ?> table) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		for (Comparable<R> rowKey : rowKeys) {
			sb.append(" <tr>\n");
			for (Comparable<C> colKey : colKeys) {
				sb.append(format("  <td>%s</td>", table.get(rowKey, colKey).toString()));
			}
			sb.append(" </tr>\n");
		}
		sb.append("</table>\n");
		return sb.toString();
	}
}
