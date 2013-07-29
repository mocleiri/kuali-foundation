package org.kuali.common.util.log4j.model;

import java.util.List;

public class Log4JContext {

	boolean reset;
	Log4JDebug debug;
	Log4JThreshold threshold;
	List<Log4JAppender> appenders;
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

}
