package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class DefaultSqlReader implements SqlReader {

	public static final String DEFAULT_DELIMITER = "/";
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final String DEFAULT_COMMENT_TOKEN = "#";

	String delimiter = DEFAULT_DELIMITER;
	LineSeparator lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = true;
	boolean ignoreComments;
	String commentToken = DEFAULT_COMMENT_TOKEN;

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
		return getReturnValue(sb.toString());
	}

	protected String getReturnValue(String sql) {
		if (trim) {
			sql = StringUtils.trimToNull(sql);
		}
		if (sql == null) {
			return null;
		} else if (StringUtils.endsWith(sql, lineSeparator.getValue())) {
			int beginIndex = 0;
			int endIndex = sql.length() - lineSeparator.getValue().length();
			return StringUtils.substring(sql, beginIndex, endIndex);
		} else {
			return sql;
		}
	}

	protected boolean ignore(String line) {
		return ignoreComments && isComment(line, commentToken);
	}

	protected boolean isComment(String line, String commentToken) {
		return StringUtils.startsWith(StringUtils.trim(line), commentToken);
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
