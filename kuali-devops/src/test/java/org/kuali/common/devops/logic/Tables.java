package org.kuali.common.devops.logic;

import static java.lang.Integer.valueOf;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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

	public static <T> void addRow(Table<Integer, String, T> table, Map<String, T> columns) {
		int row = table.rowKeySet().size();
		for (String columnKey : columns.keySet()) {
			table.put(valueOf(row), columnKey, columns.get(columnKey));
		}
	}

	public static <T> Table<Integer, String, TableCellDescriptor> getTable(List<T> elements, Class<T> type) {
		Table<Integer, String, TableCellDescriptor> table = HashBasedTable.create();
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		validate(fields, type);
		for (T element : elements) {
			Map<String, TableCellDescriptor> columns = getColumns(fields, element);
			addRow(table, columns);
		}
		return table;
	}

	protected static <T> Map<String, TableCellDescriptor> getColumns(Set<Field> fields, T element) {
		Map<String, TableCellDescriptor> columns = Maps.newHashMap();
		for (Field field : fields) {
			Optional<?> value = ReflectionUtils.get(field, element);
			TableCellDescriptor descriptor = TableCellDescriptor.create(field, value);
			columns.put(field.getName(), descriptor);
		}
		return columns;
	}

	protected static <T> void validate(Set<Field> fields, Class<T> type) {
		SortedSet<String> columns = Sets.newTreeSet();
		for (Field field : fields) {
			if (!columns.add(field.getName())) {
				throw Exceptions.illegalArgument("[%s] contains a duplicate field name -> [%s]", type.getCanonicalName(), field.getName());
			}
		}
	}

}
