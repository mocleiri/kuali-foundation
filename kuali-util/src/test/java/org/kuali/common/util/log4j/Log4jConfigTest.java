package org.kuali.common.util.log4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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

	@Test
	public void test() {
		logger.info("before");
		String pattern = "[%-4p] %m%n";
		Appender appender = getConsoleAppender(pattern);
		List<LoggerContext> contexts = Arrays.asList(getRootContext(appender), getSpringContext(appender));
		new ResetLog4JExecutable(contexts).execute();
		logger.info("after");
	}

	protected Appender getConsoleAppender(String pattern) {
		Layout layout = new PatternLayout(pattern);
		return new ConsoleAppender(layout);
	}

	protected LoggerContext getRootContext(Appender appender) {
		Level level = Level.INFO;

		LoggerContext context = new LoggerContext();
		context.setRootLogger(true);
		context.setAppenders(Arrays.asList(appender));
		context.setLevel(level);
		return context;
	}

	protected LoggerContext getSpringContext(Appender appender) {
		LoggerContext context = new LoggerContext("org.springframework", Level.ALL);
		context.setAppenders(Arrays.asList(appender));
		return context;
	}

}
