package org.kuali.common.util.log4j.spring;

import java.util.Arrays;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JCommonConfig {

	protected static final String SPRING = "org.springframework";
	protected static final String STDOUT = "stdout";

	@Bean
	public Log4JContext log4JContextDefault() {
		return getLog4JContext(Log4JPatternConstants.DEFAULT, Log4JLevelValue.INFO);
	}

	@Bean
	public Log4JContext log4JContextTest() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Log4JLevelValue.INFO);
	}

	@Bean
	public Log4JContext log4JContextDebug() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Log4JLevelValue.DEBUG);
	}

	@Bean
	public Log4JContext log4JContextMaven() {
		Log4JContext context = getLog4JContext(Log4JPatternConstants.MAVEN, Log4JLevelValue.INFO);
		Log4JLogger spring = new Log4JLogger(SPRING, new Log4JLevel(Log4JLevelValue.WARN));
		context.setLoggers(Arrays.asList(spring));
		return context;
	}

	protected Log4JContext getLog4JContext(String pattern, Log4JLevelValue level) {
		Log4JParam patternParam = new Log4JConversionPatternParam(pattern);
		Log4JLayout layout = new Log4JLayout(PatternLayout.class, Arrays.asList(patternParam));
		Log4JAppender console = new Log4JAppender(STDOUT, ConsoleAppender.class, layout);
		Log4JAppenderReference consoleReference = new Log4JAppenderReference(console.getName());
		Log4JLogger root = new Log4JLogger(Arrays.asList(consoleReference), new Log4JLevel(level));
		Log4JContext ctx = new Log4JContext(Arrays.asList(console), root);
		ctx.setReset(true);
		return ctx;
	}
}
