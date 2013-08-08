package org.kuali.common.impex.cli;

import org.junit.Test;
import org.kuali.common.impex.cli.dump.DumpDatabase;

public class DumpDatabaseTest {

	@Test
	public void test() {
		String[] args = { "classpath:jc.properties" };
		DumpDatabase.main(args);
	}

}
