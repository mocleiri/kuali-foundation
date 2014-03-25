package org.kuali.common.util.log4j.spring;

import java.util.Arrays;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.kuali.common.util.xml.service.XmlService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import({ XmlServiceConfig.class })
public class Log4JConfig {

	protected static final String SPRING = "org.springframework";
	protected static final String STDOUT = "stdout";

	@Autowired
	XmlServiceConfig xmlServiceConfig;

	@Bean
	public org.kuali.common.util.log4j.Log4JService log4jService() {
		XmlService service = xmlServiceConfig.xmlService();
		return new org.kuali.common.util.log4j.DefaultLog4JService(service);
	}

	@Bean
	public org.kuali.common.util.log4j.model.Log4JContext log4JContextDefault() {
		return getLog4JContext(org.kuali.common.util.log4j.Log4JPatternConstants.DEFAULT, org.kuali.common.util.log4j.model.Value.INFO);
	}

	@Bean
	public org.kuali.common.util.log4j.model.Log4JContext log4JContextTest() {
		return getLog4JContext(org.kuali.common.util.log4j.Log4JPatternConstants.DEBUG, org.kuali.common.util.log4j.model.Value.INFO);
	}

	@Bean
	public org.kuali.common.util.log4j.model.Log4JContext log4JContextDebug() {
		return getLog4JContext(org.kuali.common.util.log4j.Log4JPatternConstants.DEBUG, org.kuali.common.util.log4j.model.Value.DEBUG);
	}

	@Bean
	public org.kuali.common.util.log4j.model.Log4JContext log4JContextMaven() {
		org.kuali.common.util.log4j.model.Log4JContext context = getLog4JContext(org.kuali.common.util.log4j.Log4JPatternConstants.MAVEN,org.kuali.common.util.log4j.model.Value.INFO);
		// Tone down Spring logging when we are running a build
		org.kuali.common.util.log4j.model.Logger spring = new org.kuali.common.util.log4j.model.Logger(SPRING, new org.kuali.common.util.log4j.model.Level(org.kuali.common.util.log4j.model.Value.WARN));
		context.setLoggers(Arrays.asList(spring));
		return context;
	}

	protected org.kuali.common.util.log4j.model.Log4JContext getLog4JContext(String pattern, org.kuali.common.util.log4j.model.Value value) {
		org.kuali.common.util.log4j.model.Param param = new org.kuali.common.util.log4j.model.param.ConversionPatternParam(pattern);
		org.kuali.common.util.log4j.model.Layout layout = new org.kuali.common.util.log4j.model.Layout(PatternLayout.class, param);
		org.kuali.common.util.log4j.model.Appender console = new org.kuali.common.util.log4j.model.Appender(STDOUT, ConsoleAppender.class, layout);
		org.kuali.common.util.log4j.model.AppenderRef ref = new org.kuali.common.util.log4j.model.AppenderRef(console.getName());
		org.kuali.common.util.log4j.model.Logger root = new org.kuali.common.util.log4j.model.Logger(ref, new org.kuali.common.util.log4j.model.Level(value));
		return new org.kuali.common.util.log4j.model.Log4JContext(console, root, true);
	}
}
