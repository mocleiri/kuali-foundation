package org.kuali.common.util.stream.consumer;

import org.codehaus.plexus.util.cli.StreamConsumer;

public class NoOpStreamConsumer implements StreamConsumer {

	public static final NoOpStreamConsumer INSTANCE = new NoOpStreamConsumer();

	private NoOpStreamConsumer() {

	}

	@Override
	public void consumeLine(String line) {
		// Ignore the line
	}

}
