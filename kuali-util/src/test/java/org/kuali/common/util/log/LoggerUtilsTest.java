package org.kuali.common.util.log;

import org.junit.Test;
import org.slf4j.Logger;

public class LoggerUtilsTest {

	@Test
	public void test() {
		Logger logger = LoggerUtils.make();
		logger.info("Hello World");
	}

}
