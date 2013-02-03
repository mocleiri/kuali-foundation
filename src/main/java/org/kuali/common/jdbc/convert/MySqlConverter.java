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
package org.kuali.common.jdbc.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlConverter implements SqlConverter {

	private static final Logger logger = LoggerFactory.getLogger(MySqlConverter.class);
	private static final String INSERT = "INSERT INTO";
	private static final String VALUES_TOKEN = ")\n  VALUES (";

	private static final String LF = "\n";

	@Override
	public ConversionResult convert(ConversionContext context) {
		logger.debug("Converting {}", LocationUtils.getCanonicalPath(context.getOldFile()));
		File newFile = context.getNewFile();
		File oldFile = context.getOldFile();
		DefaultSqlReader reader = new DefaultSqlReader();
		reader.setDelimiter(context.getDelimiter());

		BufferedReader in = null;
		OutputStream out = null;
		try {
			in = LocationUtils.getBufferedReader(oldFile, context.getEncoding());
			out = FileUtils.openOutputStream(newFile);
			return convert(context, reader, in, out);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	protected ConversionResult convert(ConversionContext context, SqlReader reader, BufferedReader in, OutputStream out) throws IOException {
		File newFile = context.getNewFile();
		File oldFile = context.getOldFile();
		String sql = reader.getSqlStatement(in);
		StringBuilder sb = new StringBuilder();
		while (sql != null) {
			handleSql(context, sb, out, in, sql, reader);
			out.write(sb.toString().getBytes(context.getEncoding()));
			sb = new StringBuilder();
			sql = reader.getSqlStatement(in);
		}
		SqlMetaData before = getMetaData(oldFile, reader, context.getEncoding());
		SqlMetaData after = getMetaData(newFile, reader, context.getEncoding());
		return new ConversionResult(oldFile, newFile, before, after);
	}

	protected void handleSql(ConversionContext context, StringBuilder sb, OutputStream out, BufferedReader in, String sql, SqlReader reader) throws IOException {
		String trimmed = StringUtils.trim(sql);
		boolean insertStatement = isInsert(trimmed);
		if (insertStatement) {
			MorphContext mc = new MorphContext();
			mc.setSql(sql);
			mc.setReader(reader);
			mc.setInput(in);
			String combined = combineInserts(context, mc);
			sb.append(combined);
		} else {
			// Add the sql followed by linefeed->delimiter->linefeed
			sb.append(sql + LF + context.getDelimiter() + LF);
		}
	}

	protected String getInsertIntoValuesClause(String trimmed) {
		int pos = StringUtils.indexOf(trimmed, VALUES_TOKEN);
		String s = StringUtils.substring(trimmed, 0, pos);
		return s + ") VALUES ";
	}

	protected String getValues(String trimmed) {
		int pos = StringUtils.indexOf(trimmed, VALUES_TOKEN);
		if (pos == -1) {
			throw new IllegalArgumentException("Unable to parse INSERT statement");
		}
		return "(" + StringUtils.substring(trimmed, pos + VALUES_TOKEN.length());
	}

	protected void appendValues(StringBuilder sb, int count, String trimmed) {
		String values = getValues(trimmed);
		if (count > 1) {
			sb.append(",");
		}
		sb.append(values);
	}

	protected String combineInserts(ConversionContext cc, MorphContext context) throws IOException {
		String sql = context.getSql();
		StringBuilder sb = new StringBuilder();
		String trimmed = StringUtils.trimToNull(sql);
		String insertIntoValues = getInsertIntoValuesClause(trimmed);
		sb.append(insertIntoValues);
		int count = 1;
		boolean proceed = proceed(trimmed, count, sb.length(), cc);
		while (proceed) {
			appendValues(sb, count, trimmed);
			count++;
			sql = context.getReader().getSqlStatement(context.getInput());
			trimmed = StringUtils.trimToNull(sql);
			proceed = proceed(trimmed, count, sb.length(), cc);
		}
		// The last SQL statement we read was an insert
		if (isInsert(trimmed)) {
			appendValues(sb, count, trimmed);
			count++;
		}
		sb.append(LF + cc.getDelimiter() + LF);

		// There is a trailing SQL statement that is not an INSERT
		if (trimmed != null && !isInsert(trimmed)) {
			sb.append(sql + LF + cc.getDelimiter() + LF);
			count++;
		}
		return sb.toString();
	}

	protected boolean proceed(String sql, int count, int length, ConversionContext context) {
		if (sql == null) {
			return false;
		}
		if (!isInsert(sql)) {
			return false;
		}
		if (count >= context.getMaxCount()) {
			return false;
		}
		if (length >= context.getMaxLength()) {
			return false;
		}
		return true;
	}

	protected boolean isInsert(String sql) {
		return StringUtils.startsWith(sql, INSERT);
	}

	protected SqlMetaData getMetaData(File file, SqlReader reader, String encoding) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(file, encoding);
			return reader.getSqlMetaData(in);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

}
