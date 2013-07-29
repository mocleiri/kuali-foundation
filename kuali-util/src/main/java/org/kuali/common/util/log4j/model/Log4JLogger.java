package org.kuali.common.util.log4j.model;

import java.util.List;

import org.apache.log4j.Level;

public class Log4JLogger {

	public static final boolean DEFAULT_ADDITIVITY_VALUE = true;

	boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	String name;
	List<String> appenders;
	Level level;

	public boolean isAdditivity() {
		return additivity;
	}

	public void setAdditivity(boolean additivity) {
		this.additivity = additivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAppenders() {
		return appenders;
	}

	public void setAppenders(List<String> appenders) {
		this.appenders = appenders;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
