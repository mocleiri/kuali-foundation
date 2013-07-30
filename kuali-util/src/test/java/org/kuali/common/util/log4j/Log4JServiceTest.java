package org.kuali.common.util.log4j;

import java.util.Arrays;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log4j.model.Log4JAppender;
import org.kuali.common.util.log4j.model.Log4JAppenderReference;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.log4j.model.Log4JLayout;
import org.kuali.common.util.log4j.model.Log4JLevel;
import org.kuali.common.util.log4j.model.Log4JLevelValue;
import org.kuali.common.util.log4j.model.Log4JLogger;
import org.kuali.common.util.log4j.model.Log4JParam;
import org.kuali.common.util.log4j.model.Log4JPatternConstants;
import org.kuali.common.util.log4j.model.param.Log4JConversionPatternParam;
import org.kuali.common.util.log4j.spring.Log4JCommonConfig;
import org.kuali.common.util.log4j.spring.Log4JServiceConfig;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Log4JServiceConfig.class, Log4JCommonConfig.class, XmlServiceConfig.class })
public class Log4JServiceTest {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Log4JServiceTest.class);

	@Autowired
	Log4JServiceConfig log4JServiceConfig;

	@Autowired
	Log4JCommonConfig log4JCommonConfig;

	@Autowired
	XmlServiceConfig xmlServiceConfig;

	@Test
	public void testXml() {
		try {
			logger.info("before");
			Log4JParam pattern = new Log4JConversionPatternParam(Log4JPatternConstants.MAVEN);
			Log4JLayout layout = new Log4JLayout(PatternLayout.class, Arrays.asList(pattern));
			Log4JAppender console = new Log4JAppender("StdOut", ConsoleAppender.class, layout);
			Log4JAppenderReference consoleReference = new Log4JAppenderReference(console.getName());
			Log4JLogger root = new Log4JLogger(Arrays.asList(consoleReference), new Log4JLevel(Log4JLevelValue.ALL));
			Log4JLogger spring = new Log4JLogger("org.springframework", new Log4JLevel(Log4JLevelValue.ALL));
			Log4JContext ctx = new Log4JContext(Arrays.asList(console), root, Arrays.asList(spring));
			ctx.setReset(true);

			Log4JService service = log4JServiceConfig.log4jService();
			service.configure(ctx);
			logger.info("after");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test() {
		try {
			logger.info("before");
			LogManager.resetConfiguration();
			// Properties log4jProperties = getProperties();
			// PropertyConfigurator.configure(log4jProperties);
			DOMConfigurator.configure("/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-util/target/test-classes/log4j.xml");
			logger.info("after");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
