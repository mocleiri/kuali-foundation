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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartOracleLoadTest {

	private static final Logger logger = LoggerFactory.getLogger(SmartOracleLoadTest.class);
	private static final String INSERT = "INSERT";

	@Test
	public void parseSql() {
		try {
			logger.info("Parsing Old School SQL");
			String s = LocationUtils.toString("classpath:KSEN_ATP.sql");
			SqlReader reader = new DefaultSqlReader();
			BufferedReader in = LocationUtils.getBufferedReaderFromString(s);
			String sql = reader.getSqlStatement(in);
			StringBuilder sb = new StringBuilder();
			boolean firstInsert = true;
			int insertMax = 25;
			int insertCount = 0;
			while (sql != null) {
				if (StringUtils.startsWith(sql, INSERT)) {
					insertCount++;
					if (firstInsert) {
						sb.append("INSERT ALL\n");
						firstInsert = false;
					}
					sql = StringUtils.substring(sql, INSERT.length());
					sql = StringUtils.trim(sql);
					sb.append("  ");
					sb.append(sql);
					sb.append("\n\n");
					if (insertCount % insertMax == 0) {
						insertCount = 0;
						sb.append("SELECT * FROM DUAL\n");
						sb.append("/\n");
						sb.append("INSERT ALL\n");
						firstInsert = false;
					}
				} else {
					firstInsert = true;
					sb.append(sql);
					sb.append("\n");
					sb.append("/");
					sb.append("\n");
				}
				sql = reader.getSqlStatement(in);
			}
			if (!firstInsert) {
				sb.append("SELECT * FROM DUAL\n");
				sb.append("/\n");
			}
			String filename = "/Users/jeffcaddel/ws/kuali-jdbc-2.0/src/test/resources/KSEN_ATP-smart.sql";
			File file = new File(filename);
			FileUtils.writeStringToFile(file, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
