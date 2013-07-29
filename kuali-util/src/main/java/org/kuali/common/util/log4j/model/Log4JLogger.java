package org.kuali.common.util.log4j.model;

import java.util.List;

import org.apache.log4j.Level;

public class Log4JLogger {

	public static final boolean DEFAULT_ADDITIVITY_VALUE = true;

	String name;
	boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	List<Log4JAppender> appenders;
	Level level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdditivity() {
		return additivity;
	}

	public void setAdditivity(boolean additivity) {
		this.additivity = additivity;
	}

	public List<Log4JAppender> getAppenders() {
		return appenders;
	}

	public void setAppenders(List<Log4JAppender> appenders) {
		this.appenders = appenders;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
