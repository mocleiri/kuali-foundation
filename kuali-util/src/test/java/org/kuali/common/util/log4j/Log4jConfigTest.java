package org.kuali.common.util.log4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
		LogManager.shutdown();

		Level level = Level.INFO;
		Layout layout = new PatternLayout("[%-4p] %m%n");
		Appender appender = new ConsoleAppender(layout);
		
		Logger root = Logger.getRootLogger();
		root.setLevel(level);
		root.addAppender(appender);

		logger.info("after");
	}

	@SuppressWarnings("unchecked")
	protected List<Logger> getLoggers() {
		List<Logger> loggers = Collections.<Logger> list(LogManager.getCurrentLoggers());
		loggers.add(0, Logger.getRootLogger());
		return loggers;
	}

	protected void setLevel(List<Logger> loggers, Level level) {
		for (Logger logger : loggers) {
			logger.setLevel(level);
		}
	}

	protected void removeAppenders(List<Logger> loggers) {
		for (Logger logger : loggers) {
			logger.removeAllAppenders();
		}
	}

	protected List<String> getAppenders(Logger logger) {
		List<String> names = new ArrayList<String>();
		Enumeration<?> e = logger.getAllAppenders();
		while (e.hasMoreElements()) {
			Appender appender = (Appender) e.nextElement();
			names.add(appender.getName());
		}
		return names;
	}

}
