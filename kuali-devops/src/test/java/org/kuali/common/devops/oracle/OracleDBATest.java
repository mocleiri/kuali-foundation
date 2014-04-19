package org.kuali.common.devops.oracle;

import static java.lang.String.format;
import static java.net.InetAddress.getByName;
import static org.junit.Assert.fail;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.net.InetAddress;

import org.junit.Test;
import org.slf4j.Logger;

public class OracleDBATest {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		try {
			String host = "env8.ks.kuali.org";
			InetAddress addr = getByName(host);
			String ip = addr.getHostAddress();
			info("host=%s", host);
			info("ip=%s", ip);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}
