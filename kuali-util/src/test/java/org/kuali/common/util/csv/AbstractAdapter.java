package org.kuali.common.util.csv;

public abstract class AbstractAdapter<T> extends CsvAdapter<T> {

	private final CsvAdapter<String> adapter;

	@Override
	public String format(T instance) {
		if (instance == null) {
			return adapter.format(null);
		} else {
			return adapter.format(instance.toString());
		}
	}

	@Override
	public T parse(String string) {
		if (string == null) {
			return null;
		} else {
			return finishParsing(adapter.parse(string));
		}
	}

	protected abstract T finishParsing(String string);

	private AbstractAdapter(Builder<T> builder) {
		this.adapter = builder.adapter;
	}

	public static abstract class Builder<T> implements org.kuali.common.util.build.Builder<AbstractAdapter<T>> {

		private CsvAdapter<String> adapter = BasicStringAdapter.builder().build();

		public Builder<T> adapter(CsvAdapter<String> adapter) {
			this.adapter = adapter;
			return this;
		}

		public CsvAdapter<String> getAdapter() {
			return adapter;
		}

		public void setAdapter(CsvAdapter<String> adapter) {
			this.adapter = adapter;
		}

	}

	public CsvAdapter<String> getAdapter() {
		return adapter;
	}
}
