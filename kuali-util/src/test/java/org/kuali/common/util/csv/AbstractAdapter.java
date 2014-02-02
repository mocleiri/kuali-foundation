package org.kuali.common.util.csv;

public abstract class AbstractAdapter<T> extends CsvAdapter<T> {

	private final CsvAdapter<String> adapter = BasicStringAdapter.builder().build();

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
}
