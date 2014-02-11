package org.kuali.common.devops.logic.function;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.ReflectionUtils.isOptionalString;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.devops.table.TableCellDescriptor;
import org.kuali.common.util.ReflectionUtils;
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
		checkNotNull(table, "'table' cannot be null");
		SortedSet<Comparable<R>> rowKeys = Sets.newTreeSet(table.rowKeySet());
		SortedSet<Comparable<C>> colKeys = Sets.newTreeSet(table.columnKeySet());
		List<V> elements = Lists.newArrayList();
		org.apache.commons.lang3.builder.Builder<V> builder = ReflectionUtils.newInstance(getBuilderClass(targetType));
		for (Comparable<R> rowKey : rowKeys) {
			for (Comparable<C> colKey : colKeys) {
				TableCellDescriptor<String> descriptor = table.get(rowKey, colKey);
				Object value = convertString(descriptor);
				setProperty(builder, descriptor.getField().getName(), value);
			}
			V element = builder.build();
			elements.add(element);
		}
		return ImmutableList.copyOf(elements);
	}

	protected Object convertString(TableCellDescriptor<String> descriptor) {
		try {
			TypeDescriptor targetType = new TypeDescriptor(descriptor.getField());
			Field field = descriptor.getField();
			Optional<String> value = descriptor.getFieldValue();
			if (field.getName().equals("tags") && !value.isPresent()) {
				System.out.println("yo");
			}
			Object converted = converter.convert(value.orNull(), sourceType, targetType);
			// TODO Spring's converter doesn't do anything if you pass it null
			if (isOptionalString(descriptor.getField()) && converted == null) {
				converted = Optional.<String> absent();
			}
			return converted;
		} catch (Exception e) {
			throw illegalState(e, "unexpected error converting table cell string into a strongly typed object");
		}
	}

	protected void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			throw illegalState(e, "unexpected error setting bean property %s.%s=[%s]", bean.getClass().getCanonicalName(), name, value);
		}
	}

	protected org.apache.commons.lang3.builder.Builder<V> getBuilder(Class<V> targetType) {
		return ReflectionUtils.newInstance(getBuilderClass(targetType));
	}

	@SuppressWarnings("unchecked")
	protected Class<? extends org.apache.commons.lang3.builder.Builder<V>> getBuilderClass(Class<V> targetType) {
		Class<?>[] declaredClasses = targetType.getDeclaredClasses();
		for (Class<?> declaredClass : declaredClasses) {
			if (org.apache.commons.lang3.builder.Builder.class.isAssignableFrom(declaredClass)) {
				return (Class<? extends org.apache.commons.lang3.builder.Builder<V>>) declaredClass;
			}
		}
		Object[] args = { org.apache.commons.lang3.builder.Builder.class.getCanonicalName(), targetType.getCanonicalName() };
		throw illegalState("[%s] is not assignable from any classes declared in [%s]", args);
	}

	private ToListFunction(Builder<R, C, V> builder) {
		this.targetType = builder.targetType;
		this.converter = builder.converter;
		this.sourceType = builder.sourceType;
		this.locale = builder.locale;
	}

	public static <R, C, V> ToListFunction<R, C, V> create(Class<V> targetType) {
		return new Builder<R, C, V>().targetType(targetType).build();
	}

	public static <R, C, V> Builder<R, C, V> builder() {
		return new Builder<R, C, V>();
	}

	public static class Builder<R, C, V> implements org.apache.commons.lang3.builder.Builder<ToListFunction<R, C, V>> {

		private Class<V> targetType;
		private ConversionService converter = ToCsvFunction.getConversionService();
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
