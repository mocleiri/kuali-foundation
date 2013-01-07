package org.kuali.common.deploy;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeployTest {

	private static final Logger logger = LoggerFactory.getLogger(DeployTest.class);
	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			logger.trace("");
			ss.load("classpath:org/kuali/common/deploy/spring/deploy-context-test.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
