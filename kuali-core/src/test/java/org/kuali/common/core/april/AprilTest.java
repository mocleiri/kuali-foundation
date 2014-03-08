package org.kuali.common.core.april;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;

public class AprilTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			logger.info(format("hello world"));
			List<String> lines = LocationUtils.readLines("classpath:json/april-01.txt");
			logger.info("line count: %s", lines.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
