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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorphOracleSqlTest {

	private static final Logger logger = LoggerFactory.getLogger(MorphOracleSqlTest.class);
	public static final String INSERT = "INSERT";
	public static final String DELIMITER = "/";
	public static final String LF = "\n";
	public static final String CLASSPATH = "classpath:";
	public static final String INITIAL_DB = "initial-db";
	public static final String UTF8 = "UTF-8";

	@Test
	public void parseSql() {
		try {
			logger.info("Parsing Old School SQL");
			String ws = "/Users/jeffcaddel/ws/spring-db-jc";
			long start = System.currentTimeMillis();
			convert("classpath:META-INF/sql/oracle/ks-core-sql-data.resources", ws + "/ks-core/ks-core-sql/src/main/resources");
			// convert("classpath:META-INF/sql/oracle/ks-lum-sql-data.resources", ws + "/ks-lum/ks-lum-sql/src/main/resources");
			// convert("classpath:META-INF/sql/oracle/ks-enroll-sql-data.resources", ws + "/ks-enroll/ks-enroll-sql/src/main/resources");
			long elapsed = System.currentTimeMillis() - start;
			logger.info("Total Time: {}", FormatUtils.getTime(elapsed));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void convert(String locationListing, String basedir) throws IOException {
		List<String> all = LocationUtils.getLocations(locationListing);
		String token = CLASSPATH + INITIAL_DB;
		List<String> locations = getStartsWith(all, token);
		logger.info(locations.size() + "");
		for (String location : locations) {
			String filename = basedir + "/" + StringUtils.substring(location, CLASSPATH.length());
			File file = new File(filename);
			List<MorphResult> results = convert(location, file);
			show(file, results);
			break;
		}
	}

	public void show(File file, List<MorphResult> results) {
		int count = 0;
		for (MorphResult result : results) {
			count += result.getCount();
		}
		logger.info(count + " - " + file.getName());
	}

	protected List<MorphResult> convert(String location, File file) throws IOException {
		SqlReader reader = new DefaultSqlReader();
		BufferedReader in = LocationUtils.getBufferedReader(location, UTF8);
		String sql = reader.getSqlStatement(in);
		StringBuilder sb = new StringBuilder();
		OutputStream out = FileUtils.openOutputStream(file);
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
		return results;
	}

	protected MorphResult combineInserts(MorphContext context) throws IOException {
		String sql = context.getSql();
		StringBuilder sb = new StringBuilder();
		sb.append(context.getOpen());
		String trimmed = StringUtils.trimToNull(sql);
		int length = sb.length();
		int count = 0;
		boolean proceed = proceed(trimmed, count, length, context);
		while (proceed) {
			String token = "  " + trimmed + LF + LF;
			count++;
			length += token.length();
			sb.append(token);
			sql = context.getReader().getSqlStatement(context.getInput());
			trimmed = StringUtils.trimToNull(sql);
			proceed = proceed(trimmed, count, length, context);
		}
		sb.append(context.getClose());

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

	protected boolean isQuitCombiningSql(MorphContext context, int count, long length) {
		if (count > context.getMaxCount()) {
			return true;
		}
		if (length > context.getMaxLength()) {
			return true;
		}
		return false;
	}

	protected int checkLength(String token, int maxLength, int currentLength, StringBuilder sb, String open, String close, int count, int maxCount) {
		int totalLength = currentLength + token.length();
		if (totalLength > maxLength || count > maxCount) {
			sb.append(close);
			sb.append(open);
			return open.length();
		}
		return totalLength;
	}

}
