package org.kuali.common.util.log4j;

import java.io.File;
import java.util.Arrays;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectTestingConfig.class)
public class Log4jConfigTest {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Log4jConfigTest.class);

	@Autowired
	ProjectTestingConfig projectTestingConfig;

	@Bean
	public File workingDir() {
		return new File(projectTestingConfig.buildDirectory(), "log4j");
	}

	protected void info(String msg) {
		System.out.println(msg);
	}

	@Test
	public void test() {
		logger.info("before");
		new ResetLog4JExecutable(getRootContext()).execute();
		logger.info("after");
	}

	protected LoggerContext getRootContext() {
		Layout layout = new PatternLayout("[%-4p] %m%n");
		Appender appender = new ConsoleAppender(layout);
		Level level = Level.INFO;

		LoggerContext context = new LoggerContext();
		context.setRootLogger(true);
		context.setAppenders(Arrays.asList(appender));
		context.setLevel(level);
		return context;
	}

}
