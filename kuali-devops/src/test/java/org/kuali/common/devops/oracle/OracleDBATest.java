package org.kuali.common.devops.oracle;

import static org.junit.Assert.fail;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.slf4j.Logger;

public class OracleDBATest {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		try {
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
