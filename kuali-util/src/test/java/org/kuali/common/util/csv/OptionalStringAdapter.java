package org.kuali.common.util.csv;

import com.google.common.base.Optional;

public class OptionalStringAdapter extends CsvAdapter<Optional<String>> {

	private final CsvAdapter<String> adapter = new BasicStringAdapter.Builder().build();

	@Override
	public String format(Optional<String> instance) {
		return adapter.format(instance.orNull());
	}

	@Override
	public Optional<String> parse(String string) {
		return Optional.fromNullable(adapter.parse(string));
	}

}
