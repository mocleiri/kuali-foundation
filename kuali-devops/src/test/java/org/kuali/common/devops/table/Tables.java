package org.kuali.common.devops.table;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.devops.logic.Exceptions;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.LocationUtils;
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

	private static final String CHECK_CSV_DATA_LINE_MSG = "line -> %s  expected %s data elements, but there were %s data elements instead";
	private static final char CSV_SEPARATOR = ',';

	public static <T> Table<Integer, String, String> getTableFromCSV(File file) {
		return getTableFromCSV(LocationUtils.getCanonicalPath(file));
	}

	public static <T> Table<Integer, String, String> getTableFromCSV(String location) {
		checkState(!isBlank(location), "'location' cannot be blank");
		return getTableFromCSV(LocationUtils.readLines(location, Encodings.UTF8));
	}

	public static <T> Table<Integer, String, String> getTableFromCSV(List<String> lines) {
		Splitter splitter = Splitter.on(CSV_SEPARATOR);
		Table<Integer, String, String> table = HashBasedTable.create();
		List<String> columns = splitter.splitToList(lines.get(0));
		for (int row = 1; row < lines.size(); row++) {
			List<String> data = splitter.splitToList(lines.get(row));
			checkState(data.size() == columns.size(), CHECK_CSV_DATA_LINE_MSG, row, columns.size(), data.size());
			for (int column = 0; column < columns.size(); column++) {
				String columnName = columns.get(column);
				table.put(row, columnName, data.get(column));
			}
		}
		return table;
	}

	public static <T> Table<Integer, String, TableCellDescriptor<String>> getTableFromCSV(List<String> lines, Class<T> type) {
		Table<Integer, String, String> data = getTableFromCSV(lines);
		Set<String> columns = data.columnKeySet();
		Table<Integer, String, TableCellDescriptor<String>> table = HashBasedTable.create();
		Map<String, Field> fields = ReflectionUtils.getFields(type, columns);
		CsvStringFormatter formatter = CsvStringFormatter.create();
		SortedSet<Integer> rows = Sets.newTreeSet(data.rowKeySet());
		Locale locale = Locale.getDefault();
		for (Integer row : rows) {
			for (String column : columns) {
				String string = data.get(row, column);
				String parsed = formatter.parse(string, locale);
				Optional<String> fieldValue = Optional.fromNullable(parsed);
				Field field = fields.get(column);
				TableCellDescriptor<String> descriptor = TableCellDescriptor.create(field, fieldValue);
				table.put(row, column, descriptor);
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

}
