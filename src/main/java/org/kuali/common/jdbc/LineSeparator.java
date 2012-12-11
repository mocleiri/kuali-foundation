package org.kuali.common.jdbc;

public enum LineSeparator {

	CR("\r"), LF("\n"), CRLF("\r\n");

	private LineSeparator(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return this.value;
	}

}
