package org.kuali.common.util.log4j.model;

import java.util.List;

public class Log4JContext {

	public static final boolean DEFAULT_RESET_VALUE = false;
	public static final Log4JDebug DEFAULT_DEBUG_VALUE = Log4JDebug.NULL;
	public static final Log4JThreshold DEFAULT_THRESHOLD_VALUE = Log4JThreshold.NULL;

	boolean reset = DEFAULT_RESET_VALUE;
	Log4JDebug debug = Log4JDebug.NULL;
	Log4JThreshold threshold = Log4JThreshold.NULL;
	List<Log4JAppender> appenders;
	Log4JLogger root;
	List<Log4JLogger> loggers;

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public Log4JDebug getDebug() {
		return debug;
	}

	public void setDebug(Log4JDebug debug) {
		this.debug = debug;
	}

	public Log4JThreshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Log4JThreshold threshold) {
		this.threshold = threshold;
	}

	public List<Log4JAppender> getAppenders() {
		return appenders;
	}

	public void setAppenders(List<Log4JAppender> appenders) {
		this.appenders = appenders;
	}

	public List<Log4JLogger> getLoggers() {
		return loggers;
	}

	public void setLoggers(List<Log4JLogger> loggers) {
		this.loggers = loggers;
	}

	public Log4JLogger getRoot() {
		return root;
	}

	public void setRoot(Log4JLogger root) {
		this.root = root;
	}

}
