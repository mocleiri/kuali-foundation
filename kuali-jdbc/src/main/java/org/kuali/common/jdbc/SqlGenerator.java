package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlGenerator {
	final Logger logger = LoggerFactory.getLogger(SqlGenerator.class);

	private static final String DEFAULT_DELIMITER = "/";
	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String DEFAULT_ENCODING = System.getProperty("UTF-8");

	String encoding = DEFAULT_ENCODING;
	String delimiter = DEFAULT_DELIMITER;
	String lineSeparator = DEFAULT_LINE_SEPARATOR;

	public List<String> getSql(String location) {
		try {
			List<String> lines = ResourceUtils.readLines(location, encoding);
			return getSql(lines);
		} catch (IOException e) {
			throw new JdbcException(e);
		}
	}

	protected String getSql(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmed = StringUtils.isBlank(line) ? null : line.trim();
		StringBuilder sb = new StringBuilder();
		while (line != null && !delimiter.equals(trimmed)) {
			sb.append(line + lineSeparator);
		}
		return sb.toString();
	}

	protected List<String> getSql(List<String> lines) {
		List<String> sql = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String trimmed = line.trim();
			if (delimiter.equals(trimmed)) {
				sql.add(sb.toString());
				sb = new StringBuilder();
			} else {
				sb.append(line + lineSeparator);
			}
		}
		return sql;
	}

	public List<String> parseSql(String sql) {
		try {
			List<String> lines = ResourceUtils.parseLines(sql, encoding);
			return getSql(lines);
		} catch (IOException e) {
			throw new JdbcException(e);
		}
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
