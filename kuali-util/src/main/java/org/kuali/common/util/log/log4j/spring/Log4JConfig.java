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
import org.kuali.common.util.log.log4j.model.Value;
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
	XmlServiceConfig xmlServiceConfig;

	@Bean
	public Log4JService log4jService() {
		XmlService service = xmlServiceConfig.xmlService();
		return new DefaultLog4JService(service);
	}

	@Bean
	public Log4JContext log4JContextDefault() {
		return getLog4JContext(Log4JPatternConstants.DEFAULT, Value.INFO);
	}

	@Bean
	public Log4JContext log4JContextTest() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Value.INFO);
	}

	@Bean
	public Log4JContext log4JContextDebug() {
		return getLog4JContext(Log4JPatternConstants.DEBUG, Value.DEBUG);
	}

	@Bean
	public Log4JContext log4JContextMaven() {
		Logger spring = new Logger(SPRING, new Level(Value.WARN));
		return getLog4JContext(Log4JPatternConstants.MAVEN, Value.INFO, spring);
	}

	protected Log4JContext getLog4JContext(String pattern, Value value) {
		Param param = ParamFactory.getPatternParam(pattern);
		Layout layout = new Layout(PatternLayout.class, param);
		Appender console = new Appender(STDOUT, ConsoleAppender.class, layout);
		AppenderRef ref = new AppenderRef(console.getName());
		Logger root = Logger.getRootLogger(ref, new Level(value));
		return new Log4JContext(console, root, true);
	}

	protected Log4JContext getLog4JContext(String pattern, Value value, Logger logger) {
		Param param = ParamFactory.getPatternParam(pattern);
		Layout layout = new Layout(PatternLayout.class, param);
		Appender console = new Appender(STDOUT, ConsoleAppender.class, layout);
		AppenderRef ref = new AppenderRef(console.getName());
		Logger root = Logger.getRootLogger(ref, new Level(value));
		if (logger == null) {
			return new Log4JContext(console, root, true);
		} else {
			return new Log4JContext(console, root, logger, true);
		}
	}
}
