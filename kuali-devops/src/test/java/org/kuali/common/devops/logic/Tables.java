package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.Integer.valueOf;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.format.CsvStringFormatter;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
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

	public static <T> Table<Integer, String, TableCellDescriptor<String>> getTableFromCSV(List<String> lines, Class<T> type) {
		Splitter splitter = Splitter.on(',');
		Table<Integer, String, TableCellDescriptor<String>> table = HashBasedTable.create();
		Map<String, Field> fieldNames = ReflectionUtils.getUniqueFieldNames(type);
		List<String> headerTokens = splitter.splitToList(lines.get(0));
		checkState(isSuperSet(fieldNames.keySet(), Sets.newHashSet(headerTokens)), "header line contains field names not found in [%s]", type.getCanonicalName());
		CsvStringFormatter formatter = CsvStringFormatter.create();
		for (int row = 1; row < lines.size(); row++) {
			String line = lines.get(row);
			List<String> tokens = splitter.splitToList(line);
			checkState(tokens.size() == headerTokens.size(), "line -> %s  expected %s tokens, but there were %s", row, headerTokens.size(), tokens.size());
			for (int column = 0; column < tokens.size(); column++) {
				String fieldName = headerTokens.get(column);
				String token = tokens.get(column);
				String parsed = formatter.parse(token, Locale.getDefault());
				Optional<String> fieldValue = Optional.fromNullable(parsed);
				Field field = fieldNames.get(fieldName);
				TableCellDescriptor<String> descriptor = TableCellDescriptor.create(field, fieldValue);
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

	public static <T> Table<Integer, String, TableCellDescriptor<Object>> getTable(List<T> elements, Class<T> type) {
		Table<Integer, String, TableCellDescriptor<Object>> table = HashBasedTable.create();
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		checkState(ReflectionUtils.hasUniqueFieldNames(fields), "[%s] contains duplicate field names", type.getCanonicalName());
		for (T element : elements) {
			Map<String, TableCellDescriptor<Object>> columns = getColumns(fields, element);
			addRow(table, columns);
		}
		return table;
	}

	protected static <T> Map<String, TableCellDescriptor<Object>> getColumns(Set<Field> fields, T element) {
		Map<String, TableCellDescriptor<Object>> columns = Maps.newHashMap();
		for (Field field : fields) {
			Optional<Object> value = ReflectionUtils.get(field, element);
			TableCellDescriptor<Object> descriptor = TableCellDescriptor.create(field, value);
			columns.put(field.getName(), descriptor);
		}
		return columns;
	}

	/**
	 * Return true if one is a super set of two (ie every element in two is also an element in one)
	 */
	protected static <T> boolean isSuperSet(Set<T> one, Set<T> two) {
		Set<T> hash1 = Sets.newHashSet(one);
		Set<T> hash2 = Sets.newHashSet(two);
		return Sets.difference(hash2, hash1).size() == 0;
	}

	protected static <T> void validate(Set<String> fieldNames, Class<T> type, SortedSet<String> headerTokens) {
		Set<String> names = Sets.newHashSet(fieldNames);
		Set<String> tokens = Sets.newHashSet(headerTokens);
		Set<String> difference = Sets.difference(tokens, names);
		checkState(difference.size() == 0, "[%s] header tokens are not present in [%s] -> [%s]", difference.size(), type.getCanonicalName(), difference);
	}

}
