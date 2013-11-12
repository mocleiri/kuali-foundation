package org.kuali.common.util.stream.consumer;

import org.codehaus.plexus.util.cli.StreamConsumer;

public class FlattenStreamConsumer implements StreamConsumer {

	@Override
	public void consumeLine(String line) {
		System.out.print(line + " ");
	}

}
