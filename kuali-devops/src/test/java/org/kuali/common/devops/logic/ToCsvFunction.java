package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.SortedSet;

import org.kuali.common.util.csv.BasicStringAdapter;
import org.kuali.common.util.csv.CsvAdapter;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class ToCsvFunction<R, C> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, ?>, List<String>> {

	private final Joiner joiner = Joiner.on(',');

	@Override
	public List<String> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, ?> table) {
		checkNotNull(table, "'table' cannot be null");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		List<String> lines = Lists.newArrayList();
		lines.add(getHeader(colKeys));
		for (Comparable<R> rowKey : rowKeys) {
			List<String> tokens = Lists.newArrayList();
			for (Comparable<C> colKey : colKeys) {
				Object value = table.get(rowKey, colKey);
				if (value instanceof Optional) {
					Optional<?> optional = (Optional<?>) value;
					value = optional.orNull();
				}
				CsvAdapter<String> adapter = BasicStringAdapter.create();
				String s = null;
				if (value != null) {
					s = value.toString();
				}
				String string = adapter.format(s);
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
