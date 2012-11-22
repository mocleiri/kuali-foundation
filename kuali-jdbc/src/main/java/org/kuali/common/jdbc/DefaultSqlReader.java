package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;

public class DefaultSqlReader implements SqlReader {

	public static final String DEFAULT_DELIMITER = "/";
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final List<String> DEFAULT_COMMENT_TOKENS = Arrays.asList(new String[] { "#", "--" });
	public static final boolean DEFAULT_IS_TRIM = true;
	public static final boolean DEFAULT_IS_IGNORE_COMMENTS = true;

	String delimiter = DEFAULT_DELIMITER;
	LineSeparator lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = DEFAULT_IS_TRIM;
	boolean ignoreComments = DEFAULT_IS_IGNORE_COMMENTS;
	List<String> commentTokens = DEFAULT_COMMENT_TOKENS;

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		Assert.assertNotNull(delimiter, "delimiter is null");
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		StringBuilder sb = new StringBuilder();
		while (proceed(line, trimmedLine, delimiter)) {
			if (!ignore(sb, trimmedLine)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}
		return getReturnValue(sb.toString());
	}

	protected boolean isEndOfSqlStatement(String trimmedLine, String delimiter) {
		return StringUtils.equals(trimmedLine, delimiter);
	}

	protected boolean proceed(String line, String trimmedLine, String delimiter) {
		return line != null && !isEndOfSqlStatement(trimmedLine, delimiter);
	}

	protected String getReturnValue(String sql) {
		if (trim) {
			sql = StringUtils.trimToNull(sql);
		}
		if (sql == null) {
			return null;
		} else if (StringUtils.endsWith(sql, lineSeparator.getValue())) {
			int endIndex = sql.length() - lineSeparator.getValue().length();
			return StringUtils.substring(sql, 0, endIndex);
		} else {
			return sql;
		}
	}

	protected boolean ignore(StringBuilder sql, String trimmedLine) {
		return ignoreComments && StringUtils.isBlank(sql.toString()) && isSqlComment(trimmedLine, commentTokens);
	}

	protected boolean isSqlComment(String trimmedLine, List<String> commentTokens) {
		for (String commentToken : commentTokens) {
			if (StringUtils.startsWith(trimmedLine, commentToken)) {
				return true;
			}
		}
		return false;
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

	public LineSeparator getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(LineSeparator lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

}
