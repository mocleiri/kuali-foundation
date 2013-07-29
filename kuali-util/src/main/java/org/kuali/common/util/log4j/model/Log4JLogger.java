package org.kuali.common.util.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Level;

public class Log4JLogger {

	public static final Boolean DEFAULT_ADDITIVITY_VALUE = true;

	public Log4JLogger() {
		this((String) null);
	}

	public Log4JLogger(List<Log4JAppenderReference> references) {
		this(null, references);
	}

	public Log4JLogger(String name) {
		this(name, null);
	}

	public Log4JLogger(String name, List<Log4JAppenderReference> references) {
		super();
		this.name = name;
		this.references = references;
	}

	Boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	String name;
	List<Log4JAppenderReference> references;
	Level level;

	@XmlElement(name = "appender-ref")
	public List<Log4JAppenderReference> getReferences() {
		return references;
	}

	@XmlAttribute
	public Boolean getAdditivity() {
		return additivity;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setAdditivity(Boolean additivity) {
		this.additivity = additivity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setReferences(List<Log4JAppenderReference> references) {
		this.references = references;
	}

}
