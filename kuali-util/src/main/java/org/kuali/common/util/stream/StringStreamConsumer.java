package org.kuali.common.util.stream;

import org.codehaus.plexus.util.cli.StreamConsumer;

import com.google.common.base.Optional;

public class StringStreamConsumer implements StreamConsumer {

	private final StringBuilder builder = new StringBuilder();

	private static final String LS = System.getProperty("line.separator");

	@Override
	public void consumeLine(String line) {
		builder.append(line).append(LS);
	}

	public Optional<String> getOutput() {
		if (builder.length() == 0) {
			return Optional.<String> absent();
		} else {
			return Optional.of(builder.toString());
		}
	}

}
