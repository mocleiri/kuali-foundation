package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.csv.BasicStringAdapter;
import org.kuali.common.util.csv.CsvAdapter;
import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class ToCsvFunction<R, C> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor>, List<String>> {

	private final Joiner joiner = Joiner.on(',');

	@Override
	public List<String> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor> table) {
		checkNotNull(table, "'table' cannot be null");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		List<String> lines = Lists.newArrayList();
		lines.add(getHeader(colKeys));
		ConversionService converter = new DefaultConversionService();
		TypeDescriptor tds = TypeDescriptor.valueOf(String.class);
		for (Comparable<R> rowKey : rowKeys) {
			List<String> tokens = Lists.newArrayList();
			for (Comparable<C> colKey : colKeys) {
				TableCellDescriptor tcd = table.get(rowKey, colKey);
				TypeDescriptor td = new TypeDescriptor(tcd.getField());
				Object source = tcd.getObject();
				Object converted = converter.convert(source, td, tds);
				CsvAdapter<String> adapter = BasicStringAdapter.create();
				String string = adapter.format(converted.toString());
				tokens.add(string);
			}
			String joined = joiner.join(tokens);
			lines.add(joined);
		}
		return ImmutableList.copyOf(lines);
	}

	protected String getHeader(SortedSet<Comparable<C>> colKeys) {
		List<String> tokens = Lists.newArrayList();
		for (Comparable<C> colKey : colKeys) {
			tokens.add(colKey.toString());
		}
		return joiner.join(tokens);
	}

}
