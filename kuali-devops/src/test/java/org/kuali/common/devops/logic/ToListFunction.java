package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public final class ToListFunction<R, C, V> implements Function<Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor<String>>, List<V>> {

	private final Class<V> targetType;
	private final ConversionService converter;
	private final TypeDescriptor sourceType;
	private final Locale locale;

	@Override
	public List<V> apply(Table<? extends Comparable<R>, ? extends Comparable<C>, TableCellDescriptor<String>> table) {
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
					TableCellDescriptor<String> descriptor = table.get(rowKey, colKey);
					Field originalField = descriptor.getField();
					TypeDescriptor targetType = new TypeDescriptor(originalField);
					Optional<String> value = descriptor.getFieldValue();
					Object converted = converter.convert(value.orNull(), sourceType, targetType);
					Field builderField = builderClass.getDeclaredField(originalField.getName());
					PropertyUtils.setProperty(builder, builderField.getName(), converted);
				}
				V element = builder.build();
				elements.add(element);
			}
			return ImmutableList.copyOf(elements);
		} catch (Exception e) {
			throw Exceptions.illegalState(e, "unexpected error converting table of string data into strongly typed objects");
		}
	}

	private ToListFunction(Builder<R, C, V> builder) {
		this.targetType = builder.targetType;
		this.converter = builder.converter;
		this.sourceType = builder.sourceType;
		this.locale = builder.locale;
	}

	public static class Builder<R, C, V> implements org.kuali.common.util.build.Builder<ToListFunction<R, C, V>> {

		private Class<V> targetType;
		private ConversionService converter = new DefaultConversionService();
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

		public Builder<R, C, V> sourceType(TypeDescriptor sourceType) {
			this.sourceType = sourceType;
			return this;
		}

		public Builder<R, C, V> locale(Locale locale) {
			this.locale = locale;
			return this;
		}

		@Override
		public ToListFunction<R, C, V> build() {
			ToListFunction<R, C, V> instance = new ToListFunction<R, C, V>(this);
			validate(instance);
			return instance;
		}

		private static <R, C, V> void validate(ToListFunction<R, C, V> instance) {
			checkNotNull(instance.targetType, "'targetType' cannot be null");
			checkNotNull(instance.converter, "'converter' cannot be null");
			checkNotNull(instance.sourceType, "'sourceType' cannot be null");
			checkNotNull(instance.locale, "'locale' cannot be null");
		}
	}

}
