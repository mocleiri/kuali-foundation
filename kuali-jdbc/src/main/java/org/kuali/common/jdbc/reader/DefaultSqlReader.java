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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.reader.model.Comments;
import org.kuali.common.jdbc.reader.model.Delimiter;
import org.kuali.common.jdbc.reader.model.DelimiterMode;
import org.kuali.common.jdbc.reader.model.LineSeparator;
import org.kuali.common.jdbc.sql.model.SqlMetaData;
import org.kuali.common.util.Assert;

public final class DefaultSqlReader implements SqlReader {

	public DefaultSqlReader() {
		this(Delimiter.DEFAULT_DELIMITER, LineSeparator.DEFAULT_VALUE, DEFAULT_TRIM, Comments.DEFAULT_COMMENTS);
	}

	public DefaultSqlReader(String delimiter) {
		this(new Delimiter(delimiter), LineSeparator.DEFAULT_VALUE, DEFAULT_TRIM, Comments.DEFAULT_COMMENTS);
	}

	public DefaultSqlReader(DelimiterMode delimiterMode) {
		this(new Delimiter(delimiterMode), LineSeparator.DEFAULT_VALUE, DEFAULT_TRIM, Comments.DEFAULT_COMMENTS);
	}

	public DefaultSqlReader(String delimiter, DelimiterMode delimiterMode) {
		this(new Delimiter(delimiter, delimiterMode), LineSeparator.DEFAULT_VALUE, DEFAULT_TRIM, Comments.DEFAULT_COMMENTS);
	}

	public DefaultSqlReader(Delimiter delimiter, LineSeparator lineSeparator, boolean trim, Comments comments) {
		Assert.noNulls(delimiter, lineSeparator, comments);
		this.delimiter = delimiter;
		this.lineSeparator = lineSeparator;
		this.trim = trim;
		this.comments = comments;
	}

	public static final boolean DEFAULT_TRIM = true;

	private final Delimiter delimiter;
	private final LineSeparator lineSeparator;
	private final boolean trim;
	private final Comments comments;

	@Override
	public SqlMetaData getMetaData(BufferedReader reader) throws IOException {
		long count = 0;
		long size = 0;
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		while (line != null) {
			size += line.length();
			if (isEndOfSqlStatement(trimmedLine, delimiter)) {
				count++;
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}
		return new SqlMetaData(count, size);
	}

	@Override
	public String getSql(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			if (isEndOfSqlStatement(trimmedLine, delimiter)) {
				return getReturnValue(sb.toString() + trimmedLine, trim, lineSeparator);
			}
			if (!ignore(comments, sb, trimmedLine)) {
				sb.append(line + lineSeparator.getValue());
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}

		String result = getReturnValue(sb.toString(), trim, lineSeparator);

		if (result == null) {
			return null;
		} else {
			return result;
		}
	}

	protected String getReturnValue(String sql, boolean trim, LineSeparator lineSeparator) {
		if (StringUtils.endsWith(sql, delimiter.getValue())) {
			int endIndex = sql.length() - delimiter.getValue().length();
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

	protected boolean isEndOfSqlStatement(String trimmedLine, Delimiter delimiter) {
		switch (delimiter.getMode()) {
		case END_OF_LINE:
			return StringUtils.endsWith(trimmedLine, delimiter.getValue());
		case OWN_LINE:
			return StringUtils.equals(trimmedLine, delimiter.getValue());
		default:
			throw new IllegalArgumentException("Delimiter mode [" + delimiter.getMode() + "] is unknown");
		}
	}

	protected boolean proceed(String line, String trimmedLine, Delimiter delimiter) {
		if (line == null) {
			return false;
		}
		boolean endOfSqlStatement = isEndOfSqlStatement(trimmedLine, delimiter);
		return !endOfSqlStatement;
	}

	protected boolean ignore(Comments comments, StringBuilder sql, String trimmedLine) {
		if (!comments.isIgnore()) {
			return false;
		}
		if (!StringUtils.isBlank(sql.toString())) {
			return false;
		}
		return isSqlComment(trimmedLine, comments.getTokens());
	}

	protected boolean isSqlComment(String trimmedLine, List<String> commentTokens) {
		for (String commentToken : commentTokens) {
			if (StringUtils.startsWith(trimmedLine, commentToken)) {
				return true;
			}
		}
		return false;
	}

	public Delimiter getDelimiter() {
		return delimiter;
	}

	public LineSeparator getLineSeparator() {
		return lineSeparator;
	}

	public boolean isTrim() {
		return trim;
	}

	public Comments getComments() {
		return comments;
	}

}
