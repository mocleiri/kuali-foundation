package org.kuali.common.util.log4j.spring;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.kuali.common.util.log4j.Log4JPatternConstants;
import org.kuali.common.util.log4j.LoggerContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JCommonConfig {

	protected static final String SPRING = "org.springframework";

	@Bean
	public List<LoggerContext> log4JDefault() {
		return Arrays.asList(getRootLoggerContext(Log4JPatternConstants.DEFAULT));
	}

	@Bean
	public List<LoggerContext> log4JDebug() {
		return Arrays.asList(getRootLoggerContext(Log4JPatternConstants.DEBUG));
	}

	@Bean
	public List<LoggerContext> log4JMaven() {
		LoggerContext root = getRootLoggerContext(Log4JPatternConstants.MAVEN);
		LoggerContext spring = new LoggerContext(SPRING, Level.WARN, root.getAppenders());
		return Arrays.asList(root, spring);
	}

	@Bean
	public List<LoggerContext> log4JMavenDebug() {
		return Arrays.asList(getRootLoggerContext(Level.DEBUG, Log4JPatternConstants.DEBUG));
	}

	protected LoggerContext getRootLoggerContext(String pattern) {
		return getRootLoggerContext(Level.INFO, pattern);
	}

	protected LoggerContext getRootLoggerContext(Level level, String pattern) {
		Layout layout = new PatternLayout(pattern);
		List<? extends Appender> appenders = Arrays.asList(new ConsoleAppender(layout));

		LoggerContext context = new LoggerContext();
		context.setRootLogger(true);
		context.setLevel(level);
		context.setAppenders(appenders);
		return context;
	}

}
