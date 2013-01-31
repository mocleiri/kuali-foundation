package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.ignore.Ignore;

public class DefaultMySqlDumpServiceTest {

	@Test
	public void test() {
		try {
			List<Ignore> ignorers = MySqlDumpUtils.getDefaultIgnorers();

			MySqlDumpContext context = new MySqlDumpContext();
			context.setIgnorers(ignorers);
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
