package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.Integer.valueOf;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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

	public static <T> Table<Integer, String, TableCellDescriptor> getTableFromCSV(List<String> lines, Class<T> type) {
		Splitter splitter = Splitter.on(',');
		Table<Integer, String, TableCellDescriptor> table = HashBasedTable.create();
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		SortedSet<String> headerTokens = Sets.newTreeSet(splitter.splitToList(lines.get(0)));
		validate(fields, type, headerTokens);
		Map<String, Field> fieldNames = getFields(fields);
		List<String> fieldNamesList = Lists.newArrayList(headerTokens);
		for (int row = 1; row < lines.size(); row++) {
			String line = lines.get(row);
			List<String> tokens = splitter.splitToList(line);
			checkState(tokens.size() == headerTokens.size(), "line -> %s  expected %s tokens, but there were %s", row, headerTokens.size(), tokens.size());
			for (int column = 0; column < tokens.size(); column++) {
				String fieldName = fieldNamesList.get(column);
				String token = tokens.get(column);
				Field field = fieldNames.get(fieldName);
				TableCellDescriptor descriptor = TableCellDescriptor.create(field, Optional.of(token));
				table.put(row, fieldName, descriptor);
			}
		}
		return table;
	}

	protected static Map<String, Field> getFields(Set<Field> fields) {
		Map<String, Field> map = Maps.newHashMap();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map;
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

	protected static <T> void validate(Set<Field> fields, Class<T> type, SortedSet<String> headerTokens) {
		validate(fields, type);
		Set<String> fieldNames = Sets.newHashSet();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		Set<String> difference = Sets.difference(headerTokens, fieldNames);
		checkState(difference.size() == 0, "[%s] header tokens are not present in [%s] -> [%s]", difference.size(), type.getCanonicalName(), difference);
	}

	protected static <T> void validate(Set<Field> fields, Class<T> type) {
		SortedSet<String> columns = Sets.newTreeSet();
		for (Field field : fields) {
			// Make sure each field name is unique
			checkState(columns.add(field.getName()), "[%s] contains a duplicate field name -> [%s]", type.getCanonicalName(), field.getName());
		}
	}

}
