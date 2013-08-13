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
package org.kuali.common.jdbc.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.model.DelimiterMode;
import org.kuali.common.jdbc.model.LineSeparator;
import org.kuali.common.jdbc.model.SqlMetaData;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class DefaultSqlReader implements SqlReader {

	public DefaultSqlReader() {
		this(DEFAULT_DELIMITER, DelimiterMode.DEFAULT_VALUE, LineSeparator.DEFAULT_VALUE, DEFAULT_IS_TRIM, DEFAULT_IS_IGNORE_COMMENTS, DEFAULT_COMMENT_TOKENS);
	}

	public DefaultSqlReader(String delimiter) {
		this(delimiter, DelimiterMode.DEFAULT_VALUE, LineSeparator.DEFAULT_VALUE, DEFAULT_IS_TRIM, DEFAULT_IS_IGNORE_COMMENTS, DEFAULT_COMMENT_TOKENS);
	}

	public DefaultSqlReader(DelimiterMode delimiterMode) {
		this(DEFAULT_DELIMITER, delimiterMode, LineSeparator.DEFAULT_VALUE, DEFAULT_IS_TRIM, DEFAULT_IS_IGNORE_COMMENTS, DEFAULT_COMMENT_TOKENS);
	}

	public DefaultSqlReader(String delimiter, DelimiterMode delimiterMode) {
		this(delimiter, delimiterMode, LineSeparator.DEFAULT_VALUE, DEFAULT_IS_TRIM, DEFAULT_IS_IGNORE_COMMENTS, DEFAULT_COMMENT_TOKENS);
	}

	public DefaultSqlReader(String delimiter, DelimiterMode delimiterMode, LineSeparator lineSeparator, boolean trim, boolean ignoreComments, List<String> commentTokens) {
		Assert.noNulls(delimiterMode, lineSeparator, commentTokens);
		Assert.noBlanks(delimiter);
		this.delimiter = delimiter;
		this.delimiterMode = delimiterMode;
		this.lineSeparator = lineSeparator;
		this.trim = trim;
		this.ignoreComments = ignoreComments;
		this.commentTokens = CollectionUtils.unmodifiableCopy(commentTokens);
	}

	public static final String DEFAULT_DELIMITER = "/";
	public static final DelimiterMode DEFAULT_DELIMITER_MODE = DelimiterMode.OWN_LINE;
	public static final LineSeparator DEFAULT_LINE_SEPARATOR = LineSeparator.LF;
	public static final List<String> DEFAULT_COMMENT_TOKENS = Arrays.asList("#", "--");
	public static final boolean DEFAULT_IS_TRIM = true;
	public static final boolean DEFAULT_IS_IGNORE_COMMENTS = true;

	private final String delimiter;
	private final DelimiterMode delimiterMode;
	private final LineSeparator lineSeparator;
	private final boolean trim;
	private final boolean ignoreComments;
	private final List<String> commentTokens;

	@Override
	public SqlMetaData getMetaData(BufferedReader reader) throws IOException {
		long count = 0;
		long size = 0;
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		while (line != null) {
			size += line.length();
			if (isEndOfSqlStatement(trimmedLine, delimiter, delimiterMode)) {
				count++;
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}
		return new SqlMetaData(count, size);
	}

	@Override
	public List<String> getSql(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			if (isEndOfSqlStatement(trimmedLine, delimiter, delimiterMode)) {
				return Arrays.asList(getReturnValue(sb.toString() + line, trim, lineSeparator));
			}
			if (!ignore(ignoreComments, sb, trimmedLine, commentTokens)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}

		String result = getReturnValue(sb.toString(), trim, lineSeparator);

		if (result == null) {
			return null;
		} else {
			return Arrays.asList(result);
		}
	}

	protected String getReturnValue(String sql, boolean trim, LineSeparator lineSeparator) {
		if (StringUtils.endsWith(sql, delimiter)) {
			int endIndex = sql.length() - delimiter.length();
			sql = StringUtils.substring(sql, 0, endIndex);
		}
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
		if (line == null) {
			return false;
		}
		boolean endOfSqlStatement = isEndOfSqlStatement(trimmedLine, delimiter, delimiterMode);
		return !endOfSqlStatement;
	}

	protected boolean ignore(boolean ignoreComments, StringBuilder sql, String trimmedLine, List<String> commentTokens) {
		if (!ignoreComments) {
			return false;
		}
		if (!StringUtils.isBlank(sql.toString())) {
			return false;
		}
		boolean isComment = isSqlComment(trimmedLine, commentTokens);
		return isComment;
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

	public DelimiterMode getDelimiterMode() {
		return delimiterMode;
	}

	public LineSeparator getLineSeparator() {
		return lineSeparator;
	}

	public boolean isTrim() {
		return trim;
	}

	public boolean isIgnoreComments() {
		return ignoreComments;
	}

	public List<String> getCommentTokens() {
		return commentTokens;
	}

}
