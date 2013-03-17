package org.kuali.common.util.service;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class DefaultMySqlDumpServiceTest {

	@Test
	@Ignore
	public void test() {
		try {

			MySqlDumpContext context = new MySqlDumpContext();
			// The default ignorers strip out lines that cause 'noise' in the file
			// eg dump timestamps, server/client versions, etc
			// Anything that might change even though the data is the same
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
