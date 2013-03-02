package org.kuali.common.deploy;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDeployServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployServiceTest.class);
	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			logger.trace("");
			ss.load("classpath:org/kuali/common/deploy/spring/db-reset-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
