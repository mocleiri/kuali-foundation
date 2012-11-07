package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSqlReader implements SqlReader {
	final Logger logger = LoggerFactory.getLogger(DefaultSqlReader.class);

	public static final String DEFAULT_DELIMITER = "/";
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final String DEFAULT_COMMENT_TOKEN = "#";

	String delimiter = DEFAULT_DELIMITER;
	LineSeparator lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = true;
	boolean ignoreComments = true;
	String commentToken = DEFAULT_COMMENT_TOKEN;

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmed = StringUtils.isBlank(line) ? null : line.trim();
		StringBuilder sb = new StringBuilder();
		while (line != null && !delimiter.equals(trimmed)) {
			if (!ignore(line)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmed = StringUtils.isBlank(line) ? null : line.trim();
		}
		String s = (trim) ? sb.toString().trim() : sb.toString();
		if (StringUtils.isBlank(s)) {
			return null;
		} else if (s.endsWith(lineSeparator.getValue())) {
			int beginIndex = 0;
			int endIndex = s.length() - lineSeparator.getValue().length();
			return s.substring(beginIndex, endIndex);
		} else {
			return s;
		}
	}

	protected boolean ignore(String line) {
		return ignoreComments && line.trim().startsWith(commentToken);
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public boolean isIgnoreComments() {
		return ignoreComments;
	}

	public void setIgnoreComments(boolean ignoreComments) {
		this.ignoreComments = ignoreComments;
	}

	public String getCommentToken() {
		return commentToken;
	}

	public void setCommentToken(String commentToken) {
		this.commentToken = commentToken;
	}

	public LineSeparator getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(LineSeparator lineSeparator) {
		this.lineSeparator = lineSeparator;
	}
}
