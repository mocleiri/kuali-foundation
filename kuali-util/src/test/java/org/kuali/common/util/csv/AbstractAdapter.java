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
}
