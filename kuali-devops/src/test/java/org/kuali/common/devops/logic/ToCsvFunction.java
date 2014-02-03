package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.kuali.common.util.spring.format.CsvStringFormatter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.Formatter;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class ToCsvFunction<R, C> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor>, List<String>> {

	private final Joiner joiner = Joiner.on(',');
	private final ConversionService converter = new DefaultConversionService();
	private final Formatter<String> formatter = CsvStringFormatter.create();
	private final TypeDescriptor targetType = TypeDescriptor.valueOf(String.class);
	private final Locale locale = Locale.getDefault();

	@Override
	public List<String> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor> table) {
		checkNotNull(table, "'table' cannot be null");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		List<String> lines = Lists.newArrayList();
		lines.add(getHeader(colKeys));
		for (Comparable<R> rowKey : rowKeys) {
			List<String> tokens = Lists.newArrayList();
			for (Comparable<C> colKey : colKeys) {
				TableCellDescriptor descriptor = table.get(rowKey, colKey);
				String token = getToken(descriptor);
				tokens.add(token);
			}
			String joined = joiner.join(tokens);
			lines.add(joined);
		}
		return ImmutableList.copyOf(lines);
	}

	protected String getToken(TableCellDescriptor descriptor) {
		TypeDescriptor sourceType = new TypeDescriptor(descriptor.getField());
		Optional<?> value = descriptor.getValue();
		String converted = (String) converter.convert(value.orNull(), sourceType, targetType);
		return formatter.print(converted, locale);
	}

	protected String getHeader(SortedSet<Comparable<C>> colKeys) {
		List<String> tokens = Lists.newArrayList();
		for (Comparable<C> colKey : colKeys) {
			tokens.add(colKey.toString());
		}
		return joiner.join(tokens);
	}

}
