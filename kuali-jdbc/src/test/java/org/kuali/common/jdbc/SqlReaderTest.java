package org.kuali.common.jdbc;

import java.io.BufferedReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlReaderTest {
	final Logger logger = LoggerFactory.getLogger(SqlReaderTest.class);

	SqlReader sqlReader = new DefaultSqlReader();

	@Test
	public void simpleTest() {
		try {
			String sql = "\nSELECT 1\n/\n";
			BufferedReader reader = ResourceUtils.getBufferedStringReader(sql);
			String s = sqlReader.readSql(reader);
			while (s != null) {
				logger.info(JdbcUtils.flatten(s));
				s = sqlReader.readSql(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
