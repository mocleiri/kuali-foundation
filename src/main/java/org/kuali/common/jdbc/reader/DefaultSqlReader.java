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
		this.lineSeparatorLength = this.lineSeparator.getValue().length();
		this.delimiterLength = delimiter.getValue().length();
	}

	public static final boolean DEFAULT_TRIM = true;

	private final Delimiter delimiter;
	private final LineSeparator lineSeparator;
	private final boolean trim;
	private final Comments comments;
	private final int lineSeparatorLength;
	private final int delimiterLength;

	/**
	 * Extract one complete SQL statement from the BufferedReader. Return <code>null</code> after all SQL statements have been read.
	 */
	@Override
	public String getSql(BufferedReader reader) throws IOException {
		// Extract one line of text from the file
		String line = reader.readLine();

		// Trim all whitespace
		String trimmedLine = StringUtils.trimToNull(line);

		// Begin a new SQL statement
		StringBuilder sb = new StringBuilder();

		// Iterate until we have exhausted the BufferedReader
		while (line != null) {

			// Examine the trimmed line to determine if we have hit the end of a SQL statement
			// The only methods used to determine the end of a SQL statement are
			// 1 - the delimiter being on a line all on it's own with nothing else but whitespace
			// 2 - the delimiter being at the end of a line after whitespace is trimmed off
			if (isEndOfSqlStatement(trimmedLine, delimiter)) {
				// We hit the end of a SQL statement, return what we've got so far
				return getReturnValue(sb.toString() + trimmedLine, trim, lineSeparator);
			}

			// If this is a comment (and we are ignoring comments) skip this line
			if (!ignore(comments, sb, trimmedLine)) {
				// Otherwise append the line and add back in the line separator that was removed by readLine()
				sb.append(line + lineSeparator.getValue());
			}

			// Read another line of text from the file
			line = reader.readLine();

			// Trim all whitespace
			trimmedLine = StringUtils.trimToNull(line);
		}

		// There might be SQL at the end of the file
		// The trailing SQL might not be terminated by the delimiter
		String result = getReturnValue(sb.toString(), trim, lineSeparator);

		if (result == null) {
			// If there is no SQL at the end, return null
			return null;
		} else {
			// Otherwise return the final SQL statement
			return result;
		}
	}

	/**
	 * Calculate total number of SQL statements + aggregate size
	 */
	@Override
	public SqlMetaData getMetaData(BufferedReader reader) throws IOException {
		long count = 0; // Track number of individual SQL statements
		long size = 0; // Track overall size of the combined SQL statements

		// Read a line of text from the file
		String line = reader.readLine();

		// Trim all whitespace
		String trimmedLine = StringUtils.trimToNull(line);

		// Iterate until we have exhausted the BufferedReader
		while (line != null) {

			// Add the length of the current line to the overall size total
			size += line.length();

			// If this line terminates the SQL statement, increment the overall count
			if (isEndOfSqlStatement(trimmedLine, delimiter)) {
				count++;
			}

			// Read the next line from the BufferedReader
			line = reader.readLine();

			// Trim all whitespace
			trimmedLine = StringUtils.trimToNull(line);
		}

		// Return total count and overall size
		return new SqlMetaData(count, size);
	}

	protected String getReturnValue(String sql, boolean trim, LineSeparator lineSeparator) {

		// If the SQL ends with the delimiter, remove it
		if (StringUtils.endsWith(sql, delimiter.getValue())) {
			int endIndex = sql.length() - delimiterLength;
			sql = StringUtils.substring(sql, 0, endIndex);
		}

		// Trim all whitespace on either side of the SQL statement
		if (trim) {
			sql = StringUtils.trimToNull(sql);
		}

		if (sql == null) {
			// If the SQL is nothing but whitespace, return null
			return null;
		} else if (StringUtils.endsWith(sql, lineSeparator.getValue())) {
			// If the SQL ends with the line separator, remove it
			int endIndex = sql.length() - lineSeparatorLength;
			return StringUtils.substring(sql, 0, endIndex);
		} else {
			// Otherwise return the SQL as is
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
