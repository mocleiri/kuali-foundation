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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.MorphContext;
import org.kuali.common.jdbc.MorphResult;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlConverter implements SqlConverter {
	private static final Logger logger = LoggerFactory.getLogger(MySqlConverter.class);
	public static final String INSERT = "INSERT INTO";
	public static final String VALUES_TOKEN = ")\n  VALUES (";

	public static final String LF = "\n";

	@Override
	public ConversionResult convert(ConversionContext context) {
		File newFile = context.getNewFile();
		File oldFile = context.getOldFile();
		DefaultSqlReader reader = new DefaultSqlReader();
		logger.debug("Converting {}", LocationUtils.getCanonicalPath(oldFile));
		try {
			reader.setDelimiter(context.getDelimiter());
			SqlMetaData before = getMetaData(oldFile, reader, context.getEncoding());
			BufferedReader in = LocationUtils.getBufferedReader(oldFile, context.getEncoding());
			String sql = reader.getSqlStatement(in);
			StringBuilder sb = new StringBuilder();
			OutputStream out = FileUtils.openOutputStream(newFile);
			List<MorphResult> results = new ArrayList<MorphResult>();
			while (sql != null) {
				handleSql(context, sb, out, in, sql, reader, results);
				out.write(sb.toString().getBytes(context.getEncoding()));
				sb = new StringBuilder();
				sql = reader.getSqlStatement(in);
			}
			IOUtils.closeQuietly(out);
			SqlMetaData after = getMetaData(newFile, reader, context.getEncoding());
			return new ConversionResult(oldFile, newFile, before, after);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		}
	}

	protected void handleSql(ConversionContext context, StringBuilder sb, OutputStream out, BufferedReader in, String sql, SqlReader reader, List<MorphResult> results)
	        throws IOException {
		String trimmed = StringUtils.trim(sql);
		boolean insertStatement = isInsert(trimmed);
		if (insertStatement) {
			MorphContext mc = new MorphContext();
			mc.setSql(sql);
			mc.setReader(reader);
			mc.setInput(in);
			MorphResult result = combineInserts(context, mc);
			results.add(result);
			sb.append(result.getSql());
		} else {
			// Add the sql followed by linefeed->delimiter->linefeed
			sb.append(sql + LF + context.getDelimiter() + LF);
		}
	}

	protected String getIntoStatement(String trimmed) {
		return "  " + StringUtils.trim(StringUtils.substring(trimmed, INSERT.length())) + LF;
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

	protected MorphResult combineInserts(ConversionContext cc, MorphContext context) throws IOException {
		String sql = context.getSql();
		StringBuilder sb = new StringBuilder();
		String trimmed = StringUtils.trimToNull(sql);
		String insertIntoValues = getInsertIntoValuesClause(trimmed);
		sb.append(insertIntoValues);
		int count = 1;
		boolean proceed = proceed(trimmed, count, sb.length(), context);
		while (proceed) {
			appendValues(sb, count, trimmed);
			count++;
			sql = context.getReader().getSqlStatement(context.getInput());
			trimmed = StringUtils.trimToNull(sql);
			proceed = proceed(trimmed, count, sb.length(), context);
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

		MorphResult result = new MorphResult();
		result.setSql(sb.toString());
		result.setCount(count);
		result.setLength(sb.length());
		return result;
	}

	protected List<String> getStartsWith(List<String> locations, String token) {
		List<String> trimmed = new ArrayList<String>();
		for (String location : locations) {
			if (StringUtils.startsWith(location, token)) {
				trimmed.add(location);
			}
		}
		return trimmed;
	}

	protected boolean proceed(String sql, int count, int length, MorphContext context) {
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
