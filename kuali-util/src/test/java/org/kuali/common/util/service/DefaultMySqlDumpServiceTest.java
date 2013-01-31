package org.kuali.common.util.service;

import java.io.File;

import org.junit.Test;

public class DefaultMySqlDumpServiceTest {

	@Test
	public void test() {
		try {

			MySqlDumpContext context = new MySqlDumpContext();
			// The default ignorers strip out lines that cause 'noise' in the file
			// eg metadata lines inserted by mysqldump that do not represent changes in the actual data
			context.setIgnorers(MySqlDumpUtils.getDefaultIgnorers());
			context.setUsername("JDBCTEST");
			context.setPassword("JDBCTEST");
			context.setHostname("localhost");
			context.setDatabase("JDBCTEST");
			context.setOutputFile(new File("/tmp/mysqldump/jtest.sql"));
			MySqlDumpService service = new DefaultMySqlDumpService();
			service.dump(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
