package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSqlReader implements SqlReader {
	final Logger logger = LoggerFactory.getLogger(DefaultSqlReader.class);

	private static final String DEFAULT_DELIMITER = "/";
	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

	String delimiter = DEFAULT_DELIMITER;
	String lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = true;

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmed = StringUtils.isBlank(line) ? null : line.trim();
		StringBuilder sb = new StringBuilder();
		while (line != null && !delimiter.equals(trimmed)) {
			sb.append(line + lineSeparator);
			line = reader.readLine();
			trimmed = StringUtils.isBlank(line) ? null : line.trim();
		}
		String s = (trim) ? sb.toString().trim() : sb.toString();
		if (StringUtils.isBlank(s)) {
			return null;
		} else if (s.endsWith(lineSeparator)) {
			int beginIndex = 0;
			int endIndex = s.length() - lineSeparator.length();
			return s.substring(beginIndex, endIndex);
		} else {
			return s;
		}
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}
}
