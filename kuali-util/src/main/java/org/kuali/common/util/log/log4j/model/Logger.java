package org.kuali.common.util.log.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;

public class Logger {

	public static final Boolean DEFAULT_ADDITIVITY = true;

	private final String name;
	private final List<AppenderRef> references;
	private final Level level;
	private final boolean additivity;

	public Logger(String name, List<AppenderRef> references, Level level) {
		this(name, references, level, DEFAULT_ADDITIVITY);
	}

	public Logger(String name, List<AppenderRef> references, Level level, boolean additivity) {
		Assert.noBlanks(name);
		Assert.noNulls(references, level);
		this.name = name;
		this.references = references;
		this.level = level;
		this.additivity = additivity;
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

	public Level getLevel() {
		return level;
	}

}
