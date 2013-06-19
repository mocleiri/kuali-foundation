/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class DefaultSqlReader implements SqlReader {

	public static final String DEFAULT_DELIMITER = "/";
	public static final DelimiterMode DEFAULT_DELIMITER_MODE = DelimiterMode.OWN_LINE;
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final List<String> DEFAULT_COMMENT_TOKENS = Arrays.asList("#", "--");
	public static final boolean DEFAULT_IS_TRIM = true;
	public static final boolean DEFAULT_IS_IGNORE_COMMENTS = true;

	String delimiter = DEFAULT_DELIMITER;
	DelimiterMode delimiterMode = DEFAULT_DELIMITER_MODE;
	LineSeparator lineSeparator = DEFAULT_LINE_SEPARATOR;
	boolean trim = DEFAULT_IS_TRIM;
	boolean ignoreComments = DEFAULT_IS_IGNORE_COMMENTS;
	List<String> commentTokens = DEFAULT_COMMENT_TOKENS;

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		Assert.notNull(delimiter, "delimiter is null");
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		StringBuilder sb = new StringBuilder();
		while (proceed(line, trimmedLine, delimiter, delimiterMode)) {
			if (!ignore(ignoreComments, sb, trimmedLine, commentTokens)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}
		return getReturnValue(sb.toString(), trim, lineSeparator);
	}

	protected String getReturnValue(String sql, boolean trim, LineSeparator lineSeparator) {
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

	protected boolean isEndOfSqlStatement(String trimmedLine, String delimiter, DelimiterMode delimiterMode) {
		switch (delimiterMode) {
		case END_OF_LINE:
			return StringUtils.endsWith(trimmedLine, delimiter);
		case OWN_LINE:
			return StringUtils.equals(trimmedLine, delimiter);
		default:
			throw new IllegalArgumentException("Delimiter mode '" + delimiterMode + "' is unknown");
		}
	}

	protected boolean proceed(String line, String trimmedLine, String delimiter, DelimiterMode delimiterMode) {
		return line != null && !isEndOfSqlStatement(trimmedLine, delimiter, delimiterMode);
	}

	protected boolean ignore(boolean ignoreComments, StringBuilder sql, String trimmedLine, List<String> commentTokens) {
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

	public DelimiterMode getDelimiterMode() {
		return delimiterMode;
	}

	public void setDelimiterMode(DelimiterMode delimiterMode) {
		this.delimiterMode = delimiterMode;
	}

	public List<String> getCommentTokens() {
		return commentTokens;
	}

	public void setCommentTokens(List<String> commentTokens) {
		this.commentTokens = commentTokens;
	}

}
