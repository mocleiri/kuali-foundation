package org.kuali.common.util.log4j;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log4j.spring.Log4JCommonConfig;
import org.kuali.common.util.log4j.spring.Log4JServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Log4JServiceConfig.class, Log4JCommonConfig.class })
public class Log4JServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(Log4JServiceTest.class);

	@Autowired
	Log4JServiceConfig log4JServiceConfig;

	@Autowired
	Log4JCommonConfig log4JCommonConfig;

	@Test
	public void test() {
		logger.info("before");
		Log4JService service = log4JServiceConfig.log4jService();
		List<LoggerContext> contexts = log4JCommonConfig.log4JDefault();
		service.configure(contexts);
		logger.info("after");
	}
}
