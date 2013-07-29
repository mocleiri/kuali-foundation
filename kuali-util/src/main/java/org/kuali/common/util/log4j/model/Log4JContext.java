package org.kuali.common.util.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Log4JContext {

	public static final Boolean DEFAULT_RESET_VALUE = null;
	public static final Log4JDebug DEFAULT_DEBUG_VALUE = Log4JDebug.NULL;
	public static final Log4JThreshold DEFAULT_THRESHOLD_VALUE = Log4JThreshold.NULL;

	Boolean reset = DEFAULT_RESET_VALUE;
	Log4JDebug debug = Log4JDebug.NULL;
	Log4JThreshold threshold = Log4JThreshold.NULL;
	List<Log4JAppender> appenders;
	Log4JLogger root;
	List<Log4JLogger> loggers;

	@XmlAttribute
	public Boolean isReset() {
		return reset;
	}

	@XmlAttribute
	public Log4JDebug getDebug() {
		return debug;
	}

	@XmlAttribute
	public Log4JThreshold getThreshold() {
		return threshold;
	}

	@XmlElement(name = "appender")
	public List<Log4JAppender> getAppenders() {
		return appenders;
	}

	@XmlElement(name = "logger")
	public List<Log4JLogger> getLoggers() {
		return loggers;
	}

	@XmlElement
	public void setRoot(Log4JLogger root) {
		this.root = root;
	}

	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	public void setDebug(Log4JDebug debug) {
		this.debug = debug;
	}

	public void setThreshold(Log4JThreshold threshold) {
		this.threshold = threshold;
	}

	public void setAppenders(List<Log4JAppender> appenders) {
		this.appenders = appenders;
	}

	public void setLoggers(List<Log4JLogger> loggers) {
		this.loggers = loggers;
	}

	public Log4JLogger getRoot() {
		return root;
	}

}
