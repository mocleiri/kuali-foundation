package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.kuali.common.util.base.Replacer;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public final class FromCsvFunction<R extends Comparable<R>, C extends Comparable<C>> implements Function<List<String>, Table<R, C, String>> {

	private final String nullToken = "${csv.null}";
	private final String emptyToken = "${csv.empty}";
	private final String optionalAbsentToken = "${optional.absent}";
	private final Replacer replacer = Replacer.builder().add("\r", "${csv.cr}").add("\n", "${csv.lf}").add(",", "${csv.comma}").build();
	private final Splitter splitter = Splitter.on(',');

	@Override
	public Table<R, C, String> apply(List<String> lines) {
		checkNotNull(lines, "'lines' cannot be null");
		Table<R, C, String> table = HashBasedTable.create();
		for (int row=0;row<lines.size();row++) {
			List<String> tokens =getTokens(lines.get(row));
			Tables.addRow(table,tokens);
		}
		return table;
	}
	
	protected List<String> getTokens(String csv) {
		List<String> tokens = Lists.newArrayList(splitter.splitToList(csv));
		for (int i=0;i<tokens.size();i++) {
			String originalToken = tokens.get(i);
			String replacedToken = getToken(originalToken);
			tokens.set(i,replacedToken);
		}
		return tokens;
	}

	protected String getToken(String tableValue) {
		if (tableValue.equals(nullToken)) {
			return optionalAbsentToken;
		}
		if (tableValue.equals(emptyToken)) {
			return "";
		}
		return replacer.replace(tableValue);
	}

}
