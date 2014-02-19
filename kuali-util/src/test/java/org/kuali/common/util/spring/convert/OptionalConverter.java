package org.kuali.common.util.spring.convert;

import static com.google.common.base.Optional.fromNullable;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.springframework.util.NumberUtils.parseNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import java.util.TimeZone;

import org.kuali.common.util.spring.format.OptionalType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public class OptionalConverter implements GenericConverter {

	private static final ConvertiblePair PAIR = new ConvertiblePair(String.class, Optional.class);
	private static final Set<ConvertiblePair> TYPES = ImmutableSet.of(PAIR);
	private static final String ABSENT = "${optional.absent}";

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return TYPES;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Class<?> type = getType(targetType);
		if (type == String.class) {
			return StringFunction.INSTANCE.apply(source.toString());
		}
		if (Number.class.isAssignableFrom(type)) {
			@SuppressWarnings("unchecked")
			Class<? extends Number> parameter = (Class<? extends Number>) type;
			return newNumberFunction(parameter).apply(source.toString());
		}
		return null;
	}

	protected Class<?> getType(TypeDescriptor targetType) {
		Optional<OptionalType> annotation = fromNullable(targetType.getAnnotation(OptionalType.class));
		if (!annotation.isPresent()) {
			return String.class;
		}
		return annotation.get().value();
	}

	@SuppressWarnings("unchecked")
	protected static <T extends Number> NumberFunction<T> newNumberFunction(Class<T> type) {
		if (type == Byte.class) {
			return (NumberFunction<T>) new NumberFunction<Byte>(Byte.class);
		}
		if (type == Short.class) {
			return (NumberFunction<T>) new NumberFunction<Short>(Short.class);
		}
		if (type == Integer.class) {
			return (NumberFunction<T>) new NumberFunction<Integer>(Integer.class);
		}
		if (type == Long.class) {
			return (NumberFunction<T>) new NumberFunction<Long>(Long.class);
		}
		if (type == BigInteger.class) {
			return (NumberFunction<T>) new NumberFunction<BigInteger>(BigInteger.class);
		}
		if (type == Float.class) {
			return (NumberFunction<T>) new NumberFunction<Float>(Float.class);
		}
		if (type == Double.class) {
			return (NumberFunction<T>) new NumberFunction<Double>(Double.class);
		}
		if (type == BigDecimal.class) {
			return (NumberFunction<T>) new NumberFunction<BigDecimal>(BigDecimal.class);
		}
		throw illegalState("unsupported number type -> [%s]", type.getCanonicalName());
	}

	private static final class NumberFunction<T extends Number> implements Function<String, Optional<T>> {

		public NumberFunction(Class<T> type) {
			this.type = checkNotNull(type, "type");
		}

		private final Class<T> type;

		@Override
		public Optional<T> apply(String text) {
			return ABSENT.equals(text) ? Optional.<T> absent() : Optional.of(parseNumber(text, type));
		}
	}

	private static enum StringFunction implements Function<String, Optional<String>> {
		INSTANCE;
		@Override
		public Optional<String> apply(String text) {
			return ABSENT.equals(text) ? Optional.<String> absent() : Optional.of(text);
		}
	}

	private static final class TimeZoneFunction implements Function<String, TimeZone> {
		@Override
		public TimeZone apply(String string) {
			return TimeZone.getTimeZone(string);
		}
	}

}
