package org.kuali.common.jdbc;

import java.io.BufferedReader;

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
	public void simpleTest() {
		try {
			SqlReader sqlReader = new DefaultSqlReader();
			String sql = "SELECT 1\r/\nSELECT 1\n/\nSELECT 1\r\n/";
			BufferedReader reader = LocationUtils.getBufferedReaderFromString(sql);
			String s = sqlReader.getSqlStatement(reader);
			while (s != null) {
				logger.info("[" + Str.flatten(s) + "]");
				s = sqlReader.getSqlStatement(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

}
