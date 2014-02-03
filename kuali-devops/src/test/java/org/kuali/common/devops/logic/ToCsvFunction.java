package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.SortedSet;

import org.kuali.common.util.csv.DefaultAdapter;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
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
		for (Comparable<R> rowKey : rowKeys) {
			List<String> tokens = Lists.newArrayList();
			for (Comparable<C> colKey : colKeys) {
				Object value = table.get(rowKey, colKey);
				DefaultAdapter adapter = DefaultAdapter.create(value.getClass());
				String string = adapter.format(value);
				tokens.add(string);
			}
			String joined = joiner.join(tokens);
			lines.add(joined);
		}
		return ImmutableList.copyOf(lines);
	}

}
