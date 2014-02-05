package org.kuali.common.devops.logic;

import java.util.SortedSet;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.devops.table.TableContext;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class Html extends Examiner {

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		return html(table, 0);
	}

	public static <R, C> String html(Table<? extends Comparable<R>, ? extends Comparable<C>, String> table, int indent) {
		return html(TableContext.builder().indent(indent).build(), table);
	}

	protected static String getBorder(Optional<Integer> border) {
		if (border.isPresent()) {
			return "border=\"" + border.get() + "\"";
		} else {
			return "";
		}
	}

	protected static <C> String getHeader(TableContext context, SortedSet<Comparable<C>> colKeys, String padding) {
		if (context.isColumnLabels()) {
			StringBuilder sb = new StringBuilder();
			for (Comparable<C> colKey : colKeys) {
				sb.append(padding + " <th>" + colKey + "</th>");
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static <R, C> String html(TableContext context, Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		String padding = StringUtils.repeat(" ", context.getIndent());
		StringBuilder sb = new StringBuilder();
		sb.append(padding + "<table " + getBorder(context.getBorder()) + ">\n");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		sb.append(getHeader(context, colKeys, padding));
		for (Comparable<R> rowKey : rowKeys) {
			sb.append(padding + " <tr>\n");
			if (context.isRowLabels()) {
				sb.append(padding + "  <td>\n");
				sb.append(padding + "   " + rowKey.toString() + "\n");
				sb.append(padding + "  </td>\n");
			}
			for (Comparable<C> colKey : colKeys) {
				String align = context.getTdalign().isPresent() ? " align=\"" + context.getTdalign().get() + "\"" : "";
				sb.append(padding + "  <td" + align + ">\n");
				sb.append(padding + "   " + table.get(rowKey, colKey) + "\n");
				sb.append(padding + "  </td>\n");
			}
			sb.append(padding + " </tr>\n");
		}
		sb.append(padding + "</table>\n");
		return sb.toString();
	}
}
