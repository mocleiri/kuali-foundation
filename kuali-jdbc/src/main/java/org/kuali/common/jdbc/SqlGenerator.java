package org.kuali.common.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlGenerator {
	final Logger logger = LoggerFactory.getLogger(SqlGenerator.class);

	private static final String DEFAULT_DELIMITER = "/";
	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

	String encoding;
	String delimiter = DEFAULT_DELIMITER;
	String lineSeparator = DEFAULT_LINE_SEPARATOR;

	public List<String> getSql(String location) {
		try {
			List<String> lines = ResourceUtils.getLines(location, encoding);
			return getSql(lines);
		} catch (IOException e) {
			throw new JdbcException(e);
		}
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
