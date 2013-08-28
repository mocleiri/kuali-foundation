package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
public class Logger {

	public static final Boolean DEFAULT_ADDITIVITY_VALUE = true;

	Boolean additivity = DEFAULT_ADDITIVITY_VALUE;
	String name;
	List<AppenderRef> references = new ArrayList<AppenderRef>();
	Level level;

	public Logger() {
		this((String) null);
	}

	public Logger(List<AppenderRef> references) {
		this(null, references);
	}

	public Logger(AppenderRef reference, Level level) {
		this(Arrays.asList(reference), level);
	}

	public Logger(List<AppenderRef> references, Level level) {
		this(null, references, level);
	}

	public Logger(String name) {
		this(name, (Level) null);
	}

	public Logger(String name, Level level) {
		this(name, null, level);
	}

	public Logger(String name, List<AppenderRef> references) {
		this(name, references, null);
	}

	public Logger(String name, List<AppenderRef> references, Level level) {
		super();
		this.name = name;
		this.references = references;
		this.level = level;
	}

	public Logger(Logger logger) {
		super();
		this.additivity = logger.getAdditivity();
		this.name = logger.getName();
		this.level = logger.getLevel();
		for (AppenderRef reference : CollectionUtils.toEmptyList(logger.getReferences())) {
			this.references.add(new AppenderRef(reference));
		}
	}

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
