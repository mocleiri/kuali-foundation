package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.kuali.common.util.spring.format.CsvStringFormatter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.Formatter;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class FromCsvFunction<R, C, V> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor>, List<V>> {

	private final Class<V> targetType;
	private final ConversionService converter;
	private final Formatter<String> formatter;
	private final TypeDescriptor sourceType;
	private final Locale locale;

	@Override
	public List<V> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor> table) {
		try {
			checkNotNull(table, "'table' cannot be null");
			SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
			SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
			List<V> elements = Lists.newArrayList();
			@SuppressWarnings("unchecked")
			Class<? extends org.kuali.common.util.build.Builder<V>> builderClass = (Class<? extends org.kuali.common.util.build.Builder<V>>) targetType.getDeclaredClasses()[0];
			for (Comparable<R> rowKey : rowKeys) {
				org.kuali.common.util.build.Builder<V> builder = ReflectionUtils.newInstance(builderClass);
				for (Comparable<C> colKey : colKeys) {
					TableCellDescriptor descriptor = table.get(rowKey, colKey);
					Field originalField = descriptor.getField();
					TypeDescriptor targetType = new TypeDescriptor(originalField);
					Optional<?> value = descriptor.getFieldValue();
					if (value.get().toString().equals("${csv.null}")) {
						System.out.println("yo");
					}
					String formatted = format(value.get().toString(), locale);
					Object converted = converter.convert(formatted, sourceType, targetType);
					Field builderField = builderClass.getDeclaredField(originalField.getName());
					ReflectionUtils.set(builder, builderField, converted);
				}
				V element = builder.build();
				elements.add(element);
			}
			return ImmutableList.copyOf(elements);
		} catch (Exception e) {
			throw Exceptions.illegalState(e, "unexpected error converting table of csv string data into strongly typed objects");
		}
	}

	protected String format(String string, Locale locale) {
		try {
			return formatter.parse(string, locale);
		} catch (ParseException e) {
			throw Exceptions.illegalState(e, "unexpected parse error -> [%s]", string);
		}

	}

	private FromCsvFunction(Builder<R, C, V> builder) {
		this.targetType = builder.targetType;
		this.converter = builder.converter;
		this.formatter = builder.formatter;
		this.sourceType = builder.sourceType;
		this.locale = builder.locale;
	}

	public static class Builder<R, C, V> implements org.kuali.common.util.build.Builder<FromCsvFunction<R, C, V>> {

		private Class<V> targetType;
		private ConversionService converter = new DefaultConversionService();
		private Formatter<String> formatter = CsvStringFormatter.create();
		private TypeDescriptor sourceType = TypeDescriptor.valueOf(String.class);
		private Locale locale = Locale.getDefault();

		public Builder<R, C, V> targetType(Class<V> targetType) {
			this.targetType = targetType;
			return this;
		}

		public Builder<R, C, V> converter(ConversionService converter) {
			this.converter = converter;
			return this;
		}

		public Builder<R, C, V> formatter(Formatter<String> formatter) {
			this.formatter = formatter;
			return this;
		}

		public Builder<R, C, V> sourceType(TypeDescriptor sourceType) {
			this.sourceType = sourceType;
			return this;
		}

		public Builder<R, C, V> locale(Locale locale) {
			this.locale = locale;
			return this;
		}

		@Override
		public FromCsvFunction<R, C, V> build() {
			FromCsvFunction<R, C, V> instance = new FromCsvFunction<R, C, V>(this);
			validate(instance);
			return instance;
		}

		private static <R, C, V> void validate(FromCsvFunction<R, C, V> instance) {
			checkNotNull(instance.targetType, "'targetType' cannot be null");
			checkNotNull(instance.converter, "'converter' cannot be null");
			checkNotNull(instance.formatter, "'formatter' cannot be null");
			checkNotNull(instance.sourceType, "'sourceType' cannot be null");
			checkNotNull(instance.locale, "'locale' cannot be null");
		}
	}

}
