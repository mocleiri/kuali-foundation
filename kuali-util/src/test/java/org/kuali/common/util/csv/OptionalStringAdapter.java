package org.kuali.common.util.csv;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Optional;

public class OptionalStringAdapter extends CsvAdapter<Optional<String>> {

	private final CsvAdapter<String> adapter;

	@Override
	public String format(Optional<String> instance) {
		return adapter.format(instance.orNull());
	}

	@Override
	public Optional<String> parse(String string) {
		return Optional.fromNullable(adapter.parse(string));
	}

	public CsvAdapter<String> getAdapter() {
		return adapter;
	}

	private OptionalStringAdapter(Builder builder) {
		this.adapter = builder.adapter;
	}

	public static OptionalStringAdapter create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<OptionalStringAdapter> {

		private CsvAdapter<String> adapter = BasicStringAdapter.builder().build();

		public Builder adapter(CsvAdapter<String> adapter) {
			this.adapter = adapter;
			return this;
		}

		@Override
		public OptionalStringAdapter build() {
			OptionalStringAdapter instance = new OptionalStringAdapter(this);
			validate(instance);
			return instance;
		}

		private static void validate(OptionalStringAdapter instance) {
			checkNotNull(instance.adapter, "'adapter' cannot be null");
		}
	}

}
