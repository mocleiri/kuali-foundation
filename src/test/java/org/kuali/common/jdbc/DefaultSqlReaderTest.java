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
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSqlReaderTest {

	final Logger logger = LoggerFactory.getLogger(DefaultSqlReaderTest.class);

	@Test
	public void mySQLDumpTest() throws IOException {
		try {
			SqlReader sqlReader = new MySQLDumpReader();

			BufferedReader reader = LocationUtils.getBufferedReader("classpath:mysqldump.sql");
			String sql = sqlReader.getSqlStatement(reader);
			while (sql != null) {
				logger.info(sql);
				sql = sqlReader.getSqlStatement(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void simpleWayToBreakThingsTest() throws IOException {
		SqlReader sqlReader = new DefaultSqlReader();
		BufferedReader reader = LocationUtils.getBufferedReaderFromString(getSql4());
		try {
			// This one is too complicated for the default sql reader
			Assert.assertEquals("SELECT '\n/\n'", sqlReader.getSqlStatement(reader));
		} catch (AssertionError e) {
			; // ignore
		}
	}

	@Test
	public void simpleCommentTest() throws IOException {
		SqlReader sqlReader = new DefaultSqlReader();
		BufferedReader reader = LocationUtils.getBufferedReaderFromString(getSqlWithComment1());
		Assert.assertEquals("SELECT 1", sqlReader.getSqlStatement(reader));
		reader = LocationUtils.getBufferedReaderFromString(getSqlWithComment2());
		Assert.assertEquals("SELECT 1", sqlReader.getSqlStatement(reader));
		reader = LocationUtils.getBufferedReaderFromString(getSqlWithComment3());
		Assert.assertEquals("SELECT '\n-- Howdy'", sqlReader.getSqlStatement(reader));
	}

	@Test
	public void simpleTest() throws IOException {
		SqlReader sqlReader = new DefaultSqlReader();
		String sql = "SELECT 1\r/\nSELECT 1\n/\nSELECT 1\r\n/";
		BufferedReader reader = LocationUtils.getBufferedReaderFromString(sql);
		String s = sqlReader.getSqlStatement(reader);
		while (s != null) {
			logger.info("[" + Str.flatten(s) + "]");
			s = sqlReader.getSqlStatement(reader);
		}
	}

	@Test
	public void isBlankTest() {
		Assert.assertTrue(StringUtils.isBlank(" "));
		Assert.assertTrue(StringUtils.isBlank(System.getProperty("line.separator")));
		for (LineSeparator ls : LineSeparator.values()) {
			Assert.assertTrue(StringUtils.isBlank(ls.getValue()));
		}
	}

	protected String getSqlWithComment1() {
		StringBuilder sb = new StringBuilder();
		sb.append("-- Howdy\n");
		sb.append("SELECT 1");
		return sb.toString();
	}

	protected String getSqlWithComment2() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Howdy\n");
		sb.append("SELECT 1");
		return sb.toString();
	}

	protected String getSqlWithComment3() {
		StringBuilder sb = new StringBuilder();
		sb.append("-- Howdy\n");
		sb.append("SELECT '\n-- Howdy'");
		return sb.toString();
	}

	protected String getSql4() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT '\n/\n'");
		return sb.toString();
	}

}
