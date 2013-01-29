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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartOracleLoadTest {

	private static final Logger logger = LoggerFactory.getLogger(SmartOracleLoadTest.class);
	private static final String INSERT = "INSERT";
	private static final String DELIMITER = "/";
	private static final String LF = "\n";

	@Test
	public void parseSql() {
		try {
			String delimiter = "/";
			logger.info("Parsing Old School SQL");
			String s = LocationUtils.toString("classpath:KSEN_ATP.sql");
			SqlReader reader = new DefaultSqlReader();
			BufferedReader in = LocationUtils.getBufferedReaderFromString(s);
			String sql = reader.getSqlStatement(in);
			StringBuilder sb = new StringBuilder();
			while (sql != null) {
				String trimmed = StringUtils.trim(sql);
				boolean insertStatement = isInsert(trimmed);
				if (insertStatement) {
					String batchInsert = getBatchInsert(sql, reader, in, delimiter);
					sb.append(batchInsert);
				} else {
					// Add the sql followed by a linefeed + the delimiter on it's own line
					sb.append(sql + LF + DELIMITER + LF);
				}
				sql = reader.getSqlStatement(in);
			}
			String filename = "/Users/jeffcaddel/ws/kuali-jdbc-2.0/src/test/resources/KSEN_ATP-smart.sql";
			File file = new File(filename);
			FileUtils.writeStringToFile(file, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getBatchInsert(String sql, SqlReader reader, BufferedReader in, String delimiter) throws IOException {
		String open = "INSERT ALL" + LF + LF;
		String close = "SELECT * FROM DUAL" + LF + DELIMITER + LF;
		int maxLength = 1024 * 1024;

		StringBuilder sb = new StringBuilder();
		sb.append(open);
		String trimmed = StringUtils.trimToNull(sql);
		boolean insertStatement = isInsert(trimmed);
		int length = sb.length();
		while (insertStatement && sql != null) {
			String token = "  " + trimmed + LF + LF;
			length = checkLength(token, maxLength, length, sb, open, close);
			sb.append(token);
			sql = reader.getSqlStatement(in);
			trimmed = StringUtils.trimToNull(sql);
			insertStatement = isInsert(trimmed);
		}
		sb.append(close);

		// There is a trailing SQL statement that is not an INSERT
		if (sql != null) {
			sb.append(sql + LF + DELIMITER + LF);
		}
		return sb.toString();
	}

	protected boolean isInsert(String sql) {
		return StringUtils.startsWith(sql, INSERT);
	}

	protected int checkLength(String token, int maxLength, int currentLength, StringBuilder sb, String open, String close) {
		int totalLength = currentLength + token.length();
		if (totalLength > maxLength) {
			sb.append(close);
			sb.append(open);
			return open.length();
		}
		return totalLength;
	}

}
