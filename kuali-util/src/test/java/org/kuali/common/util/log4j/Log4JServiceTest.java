package org.kuali.common.util.log4j;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log4j.model.Log4JPatternConstants;
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
			LogManager.resetConfiguration();
			Properties log4jProperties = getProperties();
			PropertyConfigurator.configure(log4jProperties);
			// DOMConfigurator.configure("/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-util/target/test-classes/log4j.xml");
			logger.info("after");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Properties getProperties() {
		String appenderName = "StdOut";
		String appenderClass = ConsoleAppender.class.getName();
		String layoutClass = PatternLayout.class.getName();
		Properties props = new Properties();
		props.setProperty("log4j.rootLogger", "DEBUG, " + appenderName);
		props.setProperty("log4j.appender." + appenderName, appenderClass);
		props.setProperty("log4j.appender." + appenderName + ".layout", layoutClass);
		props.setProperty("log4j.appender." + appenderName + ".layout.ConversionPattern", Log4JPatternConstants.MAVEN);
		return props;
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
