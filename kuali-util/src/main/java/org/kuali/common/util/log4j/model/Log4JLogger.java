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

	public Log4JLogger(List<AppenderRef> references) {
		this(null, references);
	}

	public Log4JLogger(List<AppenderRef> references, Level level) {
		this(null, references, level);
	}

	public Log4JLogger(String name) {
		this(name, (Level) null);
	}

	public Log4JLogger(String name, Level level) {
		this(name, null, level);
	}

	public Log4JLogger(String name, List<AppenderRef> references) {
		this(name, references, null);
	}

	public Log4JLogger(String name, List<AppenderRef> references, Level level) {
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
		for (AppenderRef reference : CollectionUtils.toEmptyList(logger.getReferences())) {
			this.references.add(new AppenderRef(reference));
		}
	}

	Boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	String name;
	List<AppenderRef> references = new ArrayList<AppenderRef>();
	Level level;

	@XmlElement(name = "appender-ref")
	public List<AppenderRef> getReferences() {
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

	public void setReferences(List<AppenderRef> references) {
		this.references = references;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
