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

	public static final Boolean DEFAULT_RESET = false;
	public static final Boolean DEFAULT_DEBUG = false;
	public static final Value DEFAULT_THRESHOLD = Value.NULL;
	public static final List<Logger> DEFAULT_LOGGERS = Collections.<Logger> emptyList();
	public static final List<Appender> DEFAULT_APPENDERS = Collections.<Appender> emptyList();

	private final List<Appender> appenders;
	private final Logger root;
	private final List<Logger> loggers;
	private final boolean reset;
	private final boolean debug;
	private final Value threshold;

	public Log4JContext() {
		this(null, null);
	}

	public Log4JContext(List<Appender> appenders, Logger root) {
		this(appenders, root, null);
	}

	public Log4JContext(List<Appender> appenders, Logger root, List<Logger> loggers) {
		this(appenders, root, loggers, DEFAULT_RESET_VALUE);
	}

	public Log4JContext(List<Appender> appenders, Logger root, boolean reset) {
		this(appenders, root, null, reset);
	}

	public Log4JContext(Appender appender, Logger root, boolean reset) {
		this(CollectionUtils.singletonList(appender), root, DEFAULT_LOGGERS, reset, DEFAULT_DEBUG, DEFAULT_THRESHOLD);
	}

	public Log4JContext(List<Appender> appenders, Logger root, List<Logger> loggers, boolean reset, boolean debug, Value threshold) {
		Assert.noNulls(appenders, root, loggers);
		this.appenders = appenders;
		this.root = root;
		this.loggers = loggers;
		this.reset = reset;
		this.debug = debug;
		this.threshold = threshold;
	}

	@XmlAttribute
	public Boolean getReset() {
		return reset;
	}

	@XmlAttribute
	public Boolean getDebug() {
		return debug;
	}

	@XmlAttribute
	public Value getThreshold() {
		return threshold;
	}

	@XmlElement(name = "appender")
	public List<Appender> getAppenders() {
		return appenders;
	}

	@XmlElement(name = "logger")
	public List<Logger> getLoggers() {
		return loggers;
	}

	@XmlElement
	public void setRoot(Logger root) {
		this.root = root;
	}

	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public void setThreshold(Value threshold) {
		this.threshold = threshold;
	}

	public void setAppenders(List<Appender> appenders) {
		this.appenders = appenders;
	}

	public void setLoggers(List<Logger> loggers) {
		this.loggers = loggers;
	}

	public Logger getRoot() {
		return root;
	}

}
