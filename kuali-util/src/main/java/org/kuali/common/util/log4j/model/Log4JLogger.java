package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.CollectionUtils;

public class Log4JLogger {

	public static final Boolean DEFAULT_ADDITIVITY_VALUE = true;

	public Log4JLogger() {
		this((String) null);
	}

	public Log4JLogger(List<Log4JAppenderReference> references) {
		this(null, references);
	}

	public Log4JLogger(List<Log4JAppenderReference> references, Log4JLevel level) {
		this(null, references, level);
	}

	public Log4JLogger(String name) {
		this(name, null);
	}

	public Log4JLogger(String name, List<Log4JAppenderReference> references) {
		this(name, references, null);
	}

	public Log4JLogger(String name, List<Log4JAppenderReference> references, Log4JLevel level) {
		super();
		this.name = name;
		this.references = references;
		this.level = level;
	}

	public Log4JLogger(Log4JLogger logger) {
		super();
		this.additivity = logger.getAdditivity();
		this.name = logger.getName();
		this.level = logger.getLevel();
		for (Log4JAppenderReference reference : CollectionUtils.toEmptyList(logger.getReferences())) {
			this.references.add(new Log4JAppenderReference(reference));
		}
	}

	Boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	String name;
	List<Log4JAppenderReference> references = new ArrayList<Log4JAppenderReference>();
	Log4JLevel level;

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

	public void setReferences(List<Log4JAppenderReference> references) {
		this.references = references;
	}

	public Log4JLevel getLevel() {
		return level;
	}

	public void setLevel(Log4JLevel level) {
		this.level = level;
	}

}
