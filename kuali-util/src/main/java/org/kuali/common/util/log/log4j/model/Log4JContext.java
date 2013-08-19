package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class Log4JContext {

	public static final boolean DEFAULT_RESET = false;
	public static final boolean DEFAULT_DEBUG = false;
	public static final Value DEFAULT_THRESHOLD = Value.DEFAULT_VALUE;
	public static final List<Logger> NO_LOGGERS = Collections.<Logger> emptyList();
	public static final List<Appender> NO_APPENDERS = Collections.<Appender> emptyList();

	@XmlElement(name = "appender")
	private final List<Appender> appenders;

	@XmlElement
	private final Logger root;

	@XmlElement(name = "logger")
	private final List<Logger> loggers;

	@XmlAttribute
	private final boolean reset;

	@XmlAttribute
	private final boolean debug;

	@XmlAttribute
	private final Value threshold;

	@SuppressWarnings("unused")
	private Log4JContext() {
		this(NO_APPENDERS, Logger.getDefaultRootLogger());
	}

	public Log4JContext(List<Appender> appenders, Logger root) {
		this(appenders, root, DEFAULT_LOGGERS);
	}

	public Log4JContext(Appender appender, Logger root, boolean reset) {
		this(CollectionUtils.singletonList(appender), root, DEFAULT_LOGGERS, reset, DEFAULT_DEBUG, DEFAULT_THRESHOLD);
	}

	public Log4JContext(List<Appender> appenders, Logger root, List<Logger> loggers) {
		this(appenders, root, loggers, DEFAULT_RESET, DEFAULT_DEBUG, DEFAULT_THRESHOLD);
	}

	public Log4JContext(List<Appender> appenders, Logger root, List<Logger> loggers, boolean reset, boolean debug, Value threshold) {
		Assert.noNulls(appenders, root, loggers, threshold);
		this.appenders = appenders;
		this.root = root;
		this.loggers = loggers;
		this.reset = reset;
		this.debug = debug;
		this.threshold = threshold;
	}

	public boolean getReset() {
		return reset;
	}

	public boolean getDebug() {
		return debug;
	}

	public Value getThreshold() {
		return threshold;
	}

	public List<Appender> getAppenders() {
		return appenders;
	}

	public List<Logger> getLoggers() {
		return loggers;
	}

	public Logger getRoot() {
		return root;
	}

}
