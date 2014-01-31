package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.Set;

import org.kuali.common.devops.model.Environment;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Environments {

	public static Table<String, String, String> getTable(Environment env) {
		Table<String, String, String> table = HashBasedTable.create();
		table.put("row1", "col1", "a");
		table.put("row1", "col2", "b");
		table.put("row1", "col3", "c");
		table.put("row1", "col4", "d");
		table.put("row2", "col1", "1");
		table.put("row2", "col2", "2");
		table.put("row2", "col3", "3");
		table.put("row2", "col4", "4");
		return table;
	}

	public static String html(Table<?, ?, ?> table) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>\n");
		Set<?> rowKeys = table.rowKeySet();
		Set<?> colKeys = table.columnKeySet();
		for (Object rowKey : rowKeys) {
			sb.append(" <tr>\n");
			for (Object colKey : colKeys) {
				Object value = table.get(rowKey, colKey);
				sb.append(format("  <td>%s</td>", value.toString()));
			}
			sb.append(" </tr>\n");
		}
		sb.append("</table>\n");
		return sb.toString();
	}
}
