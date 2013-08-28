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
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.log.log4j.model.Logger;
import org.kuali.common.util.log.log4j.model.Param;
import org.kuali.common.util.log.log4j.model.Threshold;
import org.kuali.common.util.xml.service.XmlService;
import org.kuali.common.util.xml.spring.Log4JXmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Log4JXmlServiceConfig.class })
public class Log4JConfig {

	protected static final String SPRING = "org.springframework";
	protected static final String JAXB = "javax.xml.bind";
	protected static final String STDOUT = "stdout";

	@Autowired
	XmlService service;

	@Bean
	public Log4JService log4jService() {
		return new DefaultLog4JService(service);
	}

	@Bean
	public Log4JConfiguration log4JContextDefault() {
		return getLog4JContext(Log4JPatternConstants.DEFAULT, Threshold.INFO);
	}

	@Bean
	public Log4JConfiguration log4JContextJAXB() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Threshold.INFO);
	}

	@Bean
	public Log4JConfiguration log4JContextTest() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Threshold.INFO);
	}

	@Bean
	public Log4JConfiguration log4JContextDebug() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Threshold.DEBUG);
	}

	@Bean
	public Log4JConfiguration log4JContextMaven() {
		Logger spring = new Logger(SPRING, new Level(Threshold.WARN));
		return getLog4JContext(Log4JPatternConstants.MAVEN, Threshold.INFO, spring);
	}

	protected Log4JConfiguration getLog4JContext(String pattern, Threshold threshold) {
		return getLog4JContext(pattern, threshold, null);
	}

	protected Log4JConfiguration getLog4JContext(String pattern, Threshold threshold, Logger logger) {
		Param param = ParamFactory.getPatternParam(pattern);
		Layout layout = new Layout(PatternLayout.class, param);
		Appender console = new Appender(STDOUT, ConsoleAppender.class, layout);
		AppenderRef ref = new AppenderRef(console.getName());
		Logger root = Logger.getRootLogger(ref, new Level(threshold));
		if (logger == null) {
			return new Log4JConfiguration.Builder(root).appender(console).reset(true).build();
		} else {
			return new Log4JConfiguration.Builder(root).appender(console).logger(logger).reset(true).build();
		}
	}
}
