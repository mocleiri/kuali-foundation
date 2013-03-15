package org.kuali.common.util;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManualContextLoaderTest {

	private static final Logger logger = LoggerFactory.getLogger(ManualContextLoaderTest.class);
	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			logger.trace("");
			ss.load("classpath:org/kuali/common/util/mysqldump-wrapper-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
