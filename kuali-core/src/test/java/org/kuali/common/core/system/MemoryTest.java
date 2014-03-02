package org.kuali.common.core.system;

import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.slf4j.Logger;

public class MemoryTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		Memory mem = Memory.create();
		JsonService service = new JacksonJsonService();
		logger.info(String.format("\n%s", service.writeString(mem)));
		logger.info(String.format("used: %s free: %s", mem.getUsed(), mem.getFree()));
	}

}
