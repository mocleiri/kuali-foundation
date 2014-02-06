package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Log4JContext {

	public static final Boolean DEFAULT_RESET_VALUE = false;
	public static final Boolean DEFAULT_DEBUG_VALUE = false;
	public static final Value DEFAULT_THRESHOLD_VALUE = Value.NULL;

	Boolean reset = DEFAULT_RESET_VALUE;
	Boolean debug = DEFAULT_DEBUG_VALUE;
	Value threshold = DEFAULT_THRESHOLD_VALUE;
	Logger root;
	List<Appender> appenders = new ArrayList<Appender>();
	List<Logger> loggers = new ArrayList<Logger>();

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
		this(Arrays.asList(appender), root, null, reset);
	}

	public Log4JContext(List<Appender> appenders, Logger root, List<Logger> loggers, boolean reset) {
		super();
		this.appenders = appenders;
		this.root = root;
		this.loggers = loggers;
		this.reset = reset;
	}

	public Log4JContext(Log4JContext context) {
		super();
		this.reset = context.getReset();
		this.debug = context.getDebug();
		this.threshold = context.getThreshold();
		this.root = context.getRoot();

		for (Appender appender : CollectionUtils.toEmptyList(context.getAppenders())) {
			this.appenders.add(new Appender(appender));
		}

		for (Logger logger : CollectionUtils.toEmptyList(context.getLoggers())) {
			this.loggers.add(new Logger(logger));
		}
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
