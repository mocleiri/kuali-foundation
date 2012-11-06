package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlReader {
	final Logger logger = LoggerFactory.getLogger(SqlReader.class);

	private static final String DEFAULT_DELIMITER = "/";
	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String DEFAULT_ENCODING = System.getProperty("UTF-8");

	String encoding = DEFAULT_ENCODING;
	String delimiter = DEFAULT_DELIMITER;
	String lineSeparator = DEFAULT_LINE_SEPARATOR;

	public String readSql(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmed = StringUtils.isBlank(line) ? null : line.trim();
		StringBuilder sb = new StringBuilder();
		while (line != null && !delimiter.equals(trimmed)) {
			sb.append(line + lineSeparator);
			line = reader.readLine();
			trimmed = StringUtils.isBlank(line) ? null : line.trim();
		}
		return sb.toString();
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
