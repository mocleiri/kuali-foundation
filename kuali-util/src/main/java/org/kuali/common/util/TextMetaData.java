package org.kuali.common.util;

public class TextMetaData {

	long lines;
	long size;

	public TextMetaData() {
		this(-1, -1);
	}

	public TextMetaData(long lines, long size) {
		super();
		this.lines = lines;
		this.size = size;
	}

	public long getLines() {
		return lines;
	}

	public void setLines(long lines) {
		this.lines = lines;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
