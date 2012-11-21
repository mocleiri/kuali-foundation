package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;

public class DefaultSqlReader implements SqlReader {

	public static final String DEFAULT_DELIMITER = "/";
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final String DEFAULT_COMMENT_TOKEN = "#";
	public static final String DEFAULT_ENCODING = "UTF-8";

	String delimiter = DEFAULT_DELIMITER;
	LineSeparator lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = true;
	boolean ignoreComments;
	String commentToken = DEFAULT_COMMENT_TOKEN;
	String encoding = DEFAULT_ENCODING;

	@Override
	public BufferedReader getSqlReader(String location) {
		return LocationUtils.getBufferedReader(location, encoding);
	}

	@Override
	public BufferedReader getSqlReaderFromString(String sql) {
		return LocationUtils.getBufferedReaderFromString(sql, encoding);
	}

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmed = StringUtils.trimToNull(line);
		StringBuilder sb = new StringBuilder();
		while (line != null && !delimiter.equals(trimmed)) {
			if (!ignore(line)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmed = StringUtils.trimToNull(line);
		}
		return getReturnValue(sb);
	}

	protected String getReturnValue(StringBuilder sb) {
		String s = (trim) ? StringUtils.trim(sb.toString()) : sb.toString();
		if (StringUtils.isBlank(s)) {
			return null;
		} else if (StringUtils.endsWith(s, lineSeparator.getValue())) {
			int beginIndex = 0;
			int endIndex = s.length() - lineSeparator.getValue().length();
			return StringUtils.substring(s, beginIndex, endIndex);
		} else {
			return s;
		}
	}

	protected boolean ignore(String line) {
		return ignoreComments && StringUtils.startsWith(StringUtils.trim(line), commentToken);
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

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
