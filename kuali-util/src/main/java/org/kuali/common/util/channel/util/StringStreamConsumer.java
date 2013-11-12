package org.kuali.common.util.channel.util;

import org.codehaus.plexus.util.cli.StreamConsumer;

public class StringStreamConsumer implements StreamConsumer {

	private final StringBuilder builder = new StringBuilder();

	private String LS = System.getProperty("line.separator");

	@Override
	public void consumeLine(String line) {
		builder.append(line).append(LS);
	}

	public String getOutput() {
		return builder.toString();
	}

}
