package org.kuali.common.util.log4j.spring;

import java.util.Arrays;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.kuali.common.util.log4j.model.Appender;
import org.kuali.common.util.log4j.model.AppenderRef;
import org.kuali.common.util.log4j.model.Layout;
import org.kuali.common.util.log4j.model.Level;
import org.kuali.common.util.log4j.model.LevelValue;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.log4j.model.Logger;
import org.kuali.common.util.log4j.model.Param;
import org.kuali.common.util.log4j.model.PatternConstants;
import org.kuali.common.util.log4j.model.param.ConversionPatternParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JCommonConfig {

	protected static final String SPRING = "org.springframework";
	protected static final String STDOUT = "stdout";

	@Bean
	public Log4JContext log4JContextDefault() {
		return getLog4JContext(PatternConstants.DEFAULT, LevelValue.INFO);
	}

	@Bean
	public Log4JContext log4JContextTest() {
		return getLog4JContext(PatternConstants.DEBUG, LevelValue.INFO);
	}

	@Bean
	public Log4JContext log4JContextDebug() {
		return getLog4JContext(PatternConstants.DEBUG, LevelValue.DEBUG);
	}

	@Bean
	public Log4JContext log4JContextMaven() {
		Log4JContext context = getLog4JContext(PatternConstants.MAVEN, LevelValue.INFO);
		Logger spring = new Logger(SPRING, new Level(LevelValue.WARN));
		context.setLoggers(Arrays.asList(spring));
		return context;
	}

	protected Log4JContext getLog4JContext(String pattern, LevelValue level) {
		Param patternParam = new ConversionPatternParam(pattern);
		Layout layout = new Layout(PatternLayout.class, Arrays.asList(patternParam));
		Appender console = new Appender(STDOUT, ConsoleAppender.class, layout);
		AppenderRef consoleReference = new AppenderRef(console.getName());
		Logger root = new Logger(Arrays.asList(consoleReference), new Level(level));
		Log4JContext ctx = new Log4JContext(Arrays.asList(console), root,true);
		return ctx;
	}
}
