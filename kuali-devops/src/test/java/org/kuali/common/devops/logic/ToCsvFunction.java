package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.SortedSet;

import org.kuali.common.util.base.Replacer;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class ToCsvFunction<R, C> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, String>, List<String>> {

	private final String nullToken = "${csv.null}";
	private final String emptyToken = "${csv.empty}";
	private final String optionalAbsentToken = "${optional.absent}";
	private final Replacer replacer = Replacer.builder().add("\r", "${csv.cr}").add("\n", "${csv.lf}").add(",", "${csv.comma}").build();
	private final Joiner joiner = Joiner.on(',');

	@Override
	public List<String> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, String> table) {
		checkNotNull(table, "'table' cannot be null");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		List<String> lines = Lists.newArrayList();
		for (Comparable<R> rowKey : rowKeys) {
			List<String> tokens = Lists.newArrayList();
			for (Comparable<C> colKey : colKeys) {
				String tableValue = table.get(rowKey, colKey);
				String token = getToken(tableValue);
				tokens.add(token);
			}
			String joined = joiner.join(tokens);
			lines.add(joined);
		}
		return lines;
	}

	protected String getToken(String tableValue) {
		if (tableValue.equals(optionalAbsentToken)) {
			return nullToken;
		}
		if (tableValue.equals("")) {
			return emptyToken;
		}
		return replacer.replace(tableValue);
	}

}
