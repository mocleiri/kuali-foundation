package org.kuali.common.deploy.execute;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatController {

	private static final Logger logger = LoggerFactory.getLogger(TomcatController.class);

	@Test
	public void test() {
		try {
			logger.info("Hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
