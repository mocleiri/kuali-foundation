package org.kuali.common.core.system;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

public class VirtualRuntimeTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		VirtualRuntime runtime = VirtualRuntime.create();
		JsonService service = new JacksonJsonService();
		logger.info(format("\n%s", service.writeString(runtime)));
		Memory mem = runtime.getMemory();
		int percent = (int) ((mem.getUsed() / mem.getFree()) * 100);
		Object[] args = { runtime.getProcessors(), percent, FormatUtils.getSize(runtime.getMemory().getFree()) };
		logger.info(format("procs: %s  memory: %s%% of %s", args));
	}

}
