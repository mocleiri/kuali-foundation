package org.kuali.common.devops.table;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.Integer.valueOf;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.devops.logic.Exceptions;
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
		TableContext context = new TableContext.Builder().rows(lines.size()).fieldNames(fieldNames).columns(headerTokens.size()).headerTokens(headerTokens).formatter(formatter)
				.build();
		for (int row = 1; row < lines.size(); row++) {
			String line = lines.get(row);
			List<String> tokens = splitter.splitToList(line);
			checkState(tokens.size() == headerTokens.size(), "line -> %s  expected %s tokens, but there were %s", row, headerTokens.size(), tokens.size());
			for (int column = 0; column < tokens.size(); column++) {
				String fieldName = headerTokens.get(column);
				TableCellContext cell = TableCellContext.builder().row(row).column(column).tokens(tokens).build();
				table.put(row, fieldName, getDescriptor(context, cell));
			}
		}
		return table;
	}

	public static <T> Table<Integer, String, String> getTableFromCSV2(List<String> lines) {
		Splitter splitter = Splitter.on(',');
		Table<Integer, String, String> table = HashBasedTable.create();
		List<String> columns = splitter.splitToList(lines.get(0));
		for (int row = 1; row < lines.size(); row++) {
			List<String> data = splitter.splitToList(lines.get(row));
			checkState(data.size() == columns.size(), "line -> %s  expected %s data elements, actual data elements %s", row, columns.size(), data.size());
			for (int column = 0; column < columns.size(); column++) {
				String columnName = columns.get(column);
				table.put(row, columnName, data.get(column));
			}
		}
		return table;
	}

	protected static TableCellDescriptor<String> getDescriptor(TableContext table, TableCellContext cell) {
		String fieldName = table.getHeaderTokens().get(cell.getColumn());
		String token = cell.getTokens().get(cell.getColumn());
		String parsed = table.getFormatter().parse(token, Locale.getDefault());
		Optional<String> fieldValue = Optional.fromNullable(parsed);
		Field field = table.getFieldNames().get(fieldName);
		return TableCellDescriptor.create(field, fieldValue);
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
		checkState(ReflectionUtils.hasUniqueFieldNames(type), "[%s] contains duplicate field names", type.getCanonicalName());
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (T element : elements) {
			Map<String, TableCellDescriptor<Object>> columns = getColumns(fields, element);
			addRow(table, columns);
		}
		return table;
	}

	protected static <T> Map<String, TableCellDescriptor<Object>> getColumns(Set<Field> fields, T element) {
		Map<String, TableCellDescriptor<Object>> columns = Maps.newHashMap();
		for (Field field : fields) {
			Optional<Object> value = getProperty(element, field);
			TableCellDescriptor<Object> descriptor = TableCellDescriptor.create(field, value);
			columns.put(field.getName(), descriptor);
		}
		return columns;
	}

	protected static <T> Optional<Object> getProperty(T bean, Field field) {
		try {
			return Optional.fromNullable(PropertyUtils.getProperty(bean, field.getName()));
		} catch (Exception e) {
			throw Exceptions.illegalState(e, "unexpected error getting value for [%s.%s]", bean.getClass().getCanonicalName(), field.getName());
		}
	}

	/**
	 * Return true if one is a super set of two (ie every element in two is also an element in one)
	 */
	protected static <T> boolean isSuperSet(Set<T> one, Set<T> two) {
		Set<T> hash1 = Sets.newHashSet(one);
		Set<T> hash2 = Sets.newHashSet(two);
		return Sets.difference(hash2, hash1).size() == 0;
	}

}
