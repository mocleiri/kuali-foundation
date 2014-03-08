package org.kuali.common.core.april;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.slf4j.Logger;

public class AprilTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			logger.info(format("hello world"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
