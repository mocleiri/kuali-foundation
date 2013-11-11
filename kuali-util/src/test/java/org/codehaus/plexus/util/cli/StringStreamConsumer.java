package org.codehaus.plexus.util.cli;

public class StringStreamConsumer implements StreamConsumer {
	
	private StringBuffer string = new StringBuffer();

	private String ls = System.getProperty("line.separator");

	@Override
	public void consumeLine(String line) {
		string.append(line).append(ls);
	}

	public String getOutput() {
		return string.toString();
	}
}
