package org.kuali.common.util.log4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Log4JTestConfig.class)
public class Log4jConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(Log4jConfigTest.class);

	@Autowired
	Log4JTestConfig log4JTestConfig;

	@Test
	public void test() {
		logger.info("before");
		Executable exec = log4JTestConfig.log4jResetExecutable();
		exec.execute();
		logger.info("after");
	}
}
