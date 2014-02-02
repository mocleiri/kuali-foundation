package org.kuali.common.util.csv;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.springframework.core.convert.ConversionService;

public class DefaultAdapter<T> extends CsvAdapter<T> {

	private final CsvAdapter<String> adapter;
	private final ConversionService converter;
	private final Class<T> targetType;

	@Override
	public String format(T instance) {
		if (instance == null) {
			return adapter.format(null);
		} else {
			String converted = converter.convert(instance, String.class);
			return adapter.format(converted);
		}
	}

	@Override
	public T parse(String string) {
		if (string == null) {
			return null;
		} else {
			String parsed = adapter.parse(string);
			T converted = converter.convert(parsed, targetType);
			return converted;
		}
	}

	private DefaultAdapter(Builder<T> builder) {
		this.adapter = builder.adapter;
		this.converter = builder.converter;
		this.targetType = builder.targetType;
	}

	public static <T> DefaultAdapter<T> create(Class<T> targetType) {
		Builder<T> builder = builder();
		builder.targetType(targetType);
		return builder.build();
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<DefaultAdapter<T>> {

		private CsvAdapter<String> adapter = BasicStringAdapter.create();
		private ConversionService converter = new DefaultConversionService();
		private Class<T> targetType;

		public Builder<T> adapter(CsvAdapter<String> adapter) {
			this.adapter = adapter;
			return this;
		}

		public Builder<T> converter(ConversionService converter) {
			this.converter = converter;
			return this;
		}

		public Builder<T> targetType(Class<T> targetType) {
			this.targetType = targetType;
			return this;
		}

		@Override
		public DefaultAdapter<T> build() {
			DefaultAdapter<T> instance = new DefaultAdapter<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(DefaultAdapter<T> instance) {
			checkNotNull(instance.adapter, "'adapter' cannot be null");
			checkNotNull(instance.converter, "'converter' cannot be null");
			checkNotNull(instance.targetType, "'targetType' cannot be null");
		}

		public CsvAdapter<String> getAdapter() {
			return adapter;
		}

		public void setAdapter(CsvAdapter<String> adapter) {
			this.adapter = adapter;
		}

		public ConversionService getConverter() {
			return converter;
		}

		public void setConverter(ConversionService converter) {
			this.converter = converter;
		}

		public Class<T> getTargetType() {
			return targetType;
		}

		public void setTargetType(Class<T> targetType) {
			this.targetType = targetType;
		}
	}

	public CsvAdapter<String> getAdapter() {
		return adapter;
	}

	public ConversionService getConverter() {
		return converter;
	}

	public Class<T> getTargetType() {
		return targetType;
	}
}
