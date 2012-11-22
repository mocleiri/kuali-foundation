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

public class SqlReaderTest {
	final Logger logger = LoggerFactory.getLogger(SqlReaderTest.class);

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

}
