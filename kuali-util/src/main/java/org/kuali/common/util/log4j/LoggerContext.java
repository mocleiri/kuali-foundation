package org.kuali.common.util.log4j;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;

public class LoggerContext {

	public static final Level DEFAULT_LEVEL = Level.INFO;

	Level level = DEFAULT_LEVEL;
	boolean rootLogger;
	Class<?> loggerClass;
	List<? extends Appender> appenders;
	boolean additive;
	ResourceBundle resourceBundle;

	public Class<?> getLoggerClass() {
		return loggerClass;
	}

	public void setLoggerClass(Class<?> loggerClass) {
		this.loggerClass = loggerClass;
	}

	public List<? extends Appender> getAppenders() {
		return appenders;
	}

	public void setAppenders(List<? extends Appender> appenders) {
		this.appenders = appenders;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isAdditive() {
		return additive;
	}

	public void setAdditive(boolean additive) {
		this.additive = additive;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public boolean isRootLogger() {
		return rootLogger;
	}

	public void setRootLogger(boolean rootLogger) {
		this.rootLogger = rootLogger;
	}

}
