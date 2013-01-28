package org.kuali.common.util.service;

import java.io.File;

import org.junit.Test;

public class DefaultMySqlServiceTest {

	@Test
	public void test() {
		try {
			MySqlDumpContext context = new MySqlDumpContext();
			context.setDatabase("jdbctest");
			context.setUsername("JDBCTEST");
			context.setPassword("JDBCTEST");
			context.setHostname("localhost");
			context.setOutputFile(new File("/tmp/jdbctest.service.sql"));
			MySqlService service = new DefaultMySqlService();
			service.dump(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
