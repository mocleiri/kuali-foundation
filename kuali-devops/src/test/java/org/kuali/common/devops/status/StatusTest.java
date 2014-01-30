package org.kuali.common.devops.status;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			logger.info("Hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
