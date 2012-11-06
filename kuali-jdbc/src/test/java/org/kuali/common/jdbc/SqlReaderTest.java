package org.kuali.common.jdbc;

import java.io.BufferedReader;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlReaderTest {
	final Logger logger = LoggerFactory.getLogger(SqlReaderTest.class);

	@Test
	public void simpleTest() {
		try {
			SqlReader sqlReader = new DefaultSqlReader();
			String sql = "SELECT 1\n/\nSELECT 1\n/\nSELECT 1\n/";
			BufferedReader reader = ResourceUtils.getBufferedStringReader(sql);
			String s = sqlReader.readSql(reader);
			while (s != null) {
				logger.info("'" + JdbcUtils.flatten(s) + "'");
				s = sqlReader.readSql(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isBlankTest() {
		Assert.assertTrue(StringUtils.isBlank(" "));
		Assert.assertTrue(StringUtils.isBlank(System.getProperty("line.separator")));
	}

}
