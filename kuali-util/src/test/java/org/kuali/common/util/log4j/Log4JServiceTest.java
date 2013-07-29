package org.kuali.common.util.log4j;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log4j.spring.Log4JCommonConfig;
import org.kuali.common.util.log4j.spring.Log4JServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Log4JServiceConfig.class, Log4JCommonConfig.class })
public class Log4JServiceTest {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Log4JServiceTest.class);

	@Autowired
	Log4JServiceConfig log4JServiceConfig;

	@Autowired
	Log4JCommonConfig log4JCommonConfig;

	@Test
	public void test() {
		try {
			logger.info("before");
			System.out.println(getLoggers().size());
			LogManager.resetConfiguration();
			DOMConfigurator.configure("/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-util/target/test-classes/log4j.xml");
			System.out.println(getLoggers().size());
			logger.info("after");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Logger> getLoggers() {
		return Collections.<Logger> list(LogManager.getCurrentLoggers());
	}

	protected void showLoggers() {
		Enumeration<?> e = LogManager.getCurrentLoggers();
		while (e.hasMoreElements()) {
			Logger logger = (Logger) e.nextElement();
			System.out.println(logger.getName());
		}
	}
}
