/**
 * Copyright 2010-2014 The Kuali Foundation
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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.jdbc.convert.ConversionResult;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorphMySqlTest {
	private static final Logger logger = LoggerFactory.getLogger(MorphMySqlTest.class);
	public static final int MAX_LENGTH = new Integer(getProperty("max.length", 1024 * 50 + ""));
	public static final int MAX_COUNT = new Integer(getProperty("max.count", 50 + ""));;
	public static final String INSERT = "INSERT INTO";
	public static final String DELIMITER = ";";
	public static final String LF = "\n";
	public static final String CLASSPATH = "classpath:";
	public static final String INITIAL_DB = "initial-db";
	public static final String UTF8 = "UTF-8";
	public static final String OPEN = "INSERT ALL" + LF;
	public static final String CLOSE = "SELECT * FROM DUAL" + LF + DELIMITER + LF;
	public static final String VALUES_TOKEN = ")\n  VALUES (";
	String ws = getProperty("ws", "/Users/jeffcaddel/ws/spring-db-jc");
	int oldCount = 0;
	int newCount = 0;

	@Test
	public void parseSql() {
		try {
			logger.info("Parsing Old School SQL");
			// convert("classpath:KSEN_ATP.sql", new File("/Users/jeffcaddel/ws/kuali-jdbc-2.0/src/test/resources/KSEN_ATP-smart.sql"));
			long start = System.currentTimeMillis();
			String fragment = "/Users/jeffcaddel/ws/kuali-jdbc-2.0/src/test/resources/mysql/KRMS_PROP_PARM_T";
			File oldFile = new File(fragment + ".sql");
			File newFile = new File(fragment + "-smartly.sql");
			// convert("classpath:META-INF/sql/oracle/ks-core-sql-data.resources", ws + "/ks-core/ks-core-sql/src/main/resources");
			// convert("classpath:META-INF/sql/oracle/ks-rice-sql-data.resources", ws + "/ks-core/ks-rice-sql/src/main/resources");
			// convert("classpath:META-INF/sql/oracle/ks-lum-sql-data.resources", ws + "/ks-lum/ks-lum-sql/src/main/resources");
			// convert("classpath:META-INF/sql/oracle/ks-enroll-sql-data.resources", ws + "/ks-enroll/ks-enroll-sql/src/main/resources");
			ConversionResult result = convert(oldFile, newFile);
			long elapsed = System.currentTimeMillis() - start;
			oldCount += result.getBefore().getCount();
			newCount += result.getAfter().getCount();
			logger.info("Total Time: {}", FormatUtils.getTime(elapsed));
			System.out.println("oldCount=" + oldCount + " newCount=" + newCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void convert(String locationListing, String basedir) throws IOException {
		List<String> all = LocationUtils.getLocations(locationListing);
		String token = CLASSPATH + INITIAL_DB;
		List<String> locations = getStartsWith(all, token);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Converting " + locations.size() + " locations from [" + locationListing + "]");
		System.out.println("-----------------------------------------------------------------------------------------------");
		for (String location : locations) {
			String filename = basedir + "/" + StringUtils.substring(location, CLASSPATH.length());
			File file = new File(filename);
			ConversionResult result = convert(null, file);
			oldCount += result.getBefore().getCount();
			newCount += result.getAfter().getCount();
			show(file, result);
		}
	}

	public void show(File file, ConversionResult result) throws IOException {
		String before = StringUtils.leftPad(FormatUtils.getCount(result.getBefore().getCount()), 15, " ");
		String after = StringUtils.leftPad(FormatUtils.getCount(result.getAfter().getCount()), 15, " ");
		String token = "initial-db";
		String path = file.getCanonicalPath();
		int pos = path.indexOf(token);
		path = path.substring(pos + token.length());
		String filename = StringUtils.rightPad(path, 55, " ");
		System.out.println(filename + " - " + before + " -> " + after);
	}

	protected ConversionResult convert(File oldFile, File newFile) throws IOException {
		DefaultSqlReader reader = new DefaultSqlReader();
		reader.setDelimiter(DELIMITER);
		BufferedReader in = LocationUtils.getBufferedReader(oldFile, UTF8);
		SqlMetaData before = reader.getSqlMetaData(in);
		in.close();
		in = LocationUtils.getBufferedReader(oldFile, UTF8);
		String sql = reader.getSqlStatement(in);
		StringBuilder sb = new StringBuilder();
		OutputStream out = FileUtils.openOutputStream(newFile);
		List<MorphResult> results = new ArrayList<MorphResult>();
		while (sql != null) {
			String trimmed = StringUtils.trim(sql);
			boolean insertStatement = isInsert(trimmed);
			if (insertStatement) {
				MorphContext context = new MorphContext();
				context.setSql(sql);
				context.setReader(reader);
				context.setInput(in);
				MorphResult result = combineInserts(context);
				results.add(result);
				sb.append(result.getSql());
			} else {
				// Add the sql followed by a linefeed + the delimiter on it's own line
				sb.append(sql + LF + DELIMITER + LF);
			}
			out.write(sb.toString().getBytes(UTF8));
			sb = new StringBuilder();
			sql = reader.getSqlStatement(in);
		}
		IOUtils.closeQuietly(out);
		in = LocationUtils.getBufferedReader(newFile.getAbsolutePath());
		SqlMetaData after = reader.getSqlMetaData(in);
		in.close();
		ConversionResult cr = new ConversionResult();
		cr.setNewFile(newFile);
		cr.setOldFile(oldFile);
		cr.setAfter(after);
		cr.setBefore(before);
		return cr;
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

	protected MorphResult combineInserts(MorphContext context) throws IOException {
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
		sb.append(LF + DELIMITER + LF);

		// There is a trailing SQL statement that is not an INSERT
		if (trimmed != null && !isInsert(trimmed)) {
			sb.append(sql + LF + DELIMITER + LF);
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

	protected static String getProperty(String key, String defaultValue) {
		return System.getProperty(key) == null ? defaultValue : System.getProperty(key);
	}

}
