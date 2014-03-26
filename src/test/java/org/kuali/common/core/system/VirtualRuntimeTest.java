package org.kuali.common.core.system;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.text.NumberFormat;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

public class VirtualRuntimeTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		NumberFormat nf = NumberFormat.getPercentInstance();
		VirtualRuntime runtime = VirtualRuntime.create();
		JsonService service = new JacksonJsonService();
		logger.info(format("\n%s", service.writeString(runtime)));
		Memory mem = runtime.getMemory();
		double percent = (mem.getUsed() * 1d) / mem.getFree();
		Object[] args = { runtime.getProcessors(), nf.format(percent), FormatUtils.getSize(runtime.getMemory().getFree()) };
		logger.info(format("processors: %s  memory: %s of %s", args));
	}

}
