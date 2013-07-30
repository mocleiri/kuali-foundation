package org.kuali.common.util.log4j;

import java.util.Arrays;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log4j.model.Appender;
import org.kuali.common.util.log4j.model.AppenderRef;
import org.kuali.common.util.log4j.model.Layout;
import org.kuali.common.util.log4j.model.Level;
import org.kuali.common.util.log4j.model.LevelValue;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.log4j.model.Logger;
import org.kuali.common.util.log4j.model.Param;
import org.kuali.common.util.log4j.model.param.ConversionPatternParam;
import org.kuali.common.util.log4j.spring.Log4JConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Log4JConfig.class })
public class Log4JServiceTest {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Log4JServiceTest.class);

	@Autowired
	Log4JConfig log4JConfig;

	@Test
	public void testXml() {
		try {
			logger.info("before");
			Param pattern = new ConversionPatternParam(Log4JPatternConstants.MAVEN);
			Layout layout = new Layout(PatternLayout.class, Arrays.asList(pattern));
			Appender console = new Appender("StdOut", ConsoleAppender.class, layout);
			AppenderRef consoleReference = new AppenderRef(console.getName());
			Logger root = new Logger(Arrays.asList(consoleReference), new Level(LevelValue.ALL));
			Logger spring = new Logger("org.springframework", new Level(LevelValue.ALL));
			Log4JContext ctx = new Log4JContext(Arrays.asList(console), root, Arrays.asList(spring), true);
			Log4JService service = log4JConfig.log4jService();

			String xml = service.toXml(ctx);
			logger.info("\n\n" + xml);

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
