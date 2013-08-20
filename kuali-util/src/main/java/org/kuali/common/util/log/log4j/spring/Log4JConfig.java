package org.kuali.common.util.log.log4j.spring;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.kuali.common.util.log.log4j.DefaultLog4JService;
import org.kuali.common.util.log.log4j.Log4JPatternConstants;
import org.kuali.common.util.log.log4j.Log4JService;
import org.kuali.common.util.log.log4j.ParamFactory;
import org.kuali.common.util.log.log4j.model.Appender;
import org.kuali.common.util.log.log4j.model.AppenderRef;
import org.kuali.common.util.log.log4j.model.Layout;
import org.kuali.common.util.log.log4j.model.Level;
import org.kuali.common.util.log.log4j.model.Log4JContext;
import org.kuali.common.util.log.log4j.model.Logger;
import org.kuali.common.util.log.log4j.model.Param;
import org.kuali.common.util.log.log4j.model.Threshold;
import org.kuali.common.util.xml.XmlService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ XmlServiceConfig.class })
public class Log4JConfig {

	protected static final String SPRING = "org.springframework";
	protected static final String STDOUT = "stdout";

	@Autowired
	XmlService service;

	@Bean
	public Log4JService log4jService() {
		return new DefaultLog4JService(service);
	}

	@Bean
	public Log4JContext log4JContextDefault() {
		return getLog4JContext(Log4JPatternConstants.DEFAULT, Threshold.INFO);
	}

	@Bean
	public Log4JContext log4JContextTest() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Threshold.INFO);
	}

	@Bean
	public Log4JContext log4JContextDebug() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Threshold.DEBUG);
	}

	@Bean
	public Log4JContext log4JContextMaven() {
		Logger spring = new Logger(SPRING, new Level(Threshold.WARN));
		return getLog4JContext(Log4JPatternConstants.MAVEN, Threshold.INFO, spring);
	}

	protected Log4JContext getLog4JContext(String pattern, Threshold value) {
		return getLog4JContext(pattern, value, null);
	}

	protected Log4JContext getLog4JContext(String pattern, Threshold value, Logger logger) {
		Param param = ParamFactory.getPatternParam(pattern);
		Layout layout = new Layout(PatternLayout.class, param);
		Appender console = new Appender(STDOUT, ConsoleAppender.class, layout);
		AppenderRef ref = new AppenderRef(console.getName());
		Logger root = Logger.getRootLogger(ref, new Level(value));
		if (logger == null) {
			return new Log4JContext.Builder().appender(console).root(root).reset(true).build();
		} else {
			return new Log4JContext.Builder().appender(console).root(root).logger(logger).reset(true).build();
		}
	}
}
