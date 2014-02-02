package org.kuali.common.util.csv;

import static com.google.common.base.Preconditions.checkNotNull;

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

	public static class Builder<T> implements org.kuali.common.util.build.Builder<DefaultAdapter<T>> {

		private CsvAdapter<String> adapter;
		private ConversionService converter;
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
	}
}
