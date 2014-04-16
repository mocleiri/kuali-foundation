package org.kuali.common.devops.ci;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.core.system.VirtualSystem;
import org.slf4j.Logger;

public class CloneJenkinsStack {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		VirtualSystem vs = VirtualSystem.create();
		boolean quiet = equalsIgnoreCase(vs.getProperties().getProperty("ec2.quiet"), "false") ? false : true;
		String srcStack = vs.getProperties().getProperty("ec2.stack.src");
		String dstStack = vs.getProperties().getProperty("ec2.stack.dst");
		String region = vs.getProperties().getProperty("ec2.region");

	}

	private static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}
