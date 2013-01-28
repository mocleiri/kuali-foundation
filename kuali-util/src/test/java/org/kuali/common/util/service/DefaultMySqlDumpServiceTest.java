package org.kuali.common.util.service;

import java.io.File;

import org.junit.Test;

public class DefaultMySqlDumpServiceTest {

	@Test
	public void test() {
		try {
			MySqlDumpContext context = new MySqlDumpContext();
			context.setUsername("JDBCTEST");
			context.setPassword("JDBCTEST");
			context.setHostname("localhost");
			context.setDatabase("jdbctest");
			context.setOutputFile(new File("/tmp/mysqldump/jtest.sql"));
			MySqlDumpService service = new DefaultMySqlDumpService();
			service.dump(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
