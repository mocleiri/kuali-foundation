package org.kuali.common.util.log.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.NullUtils;

public class Logger {

	public static final boolean DEFAULT_ADDITIVITY = true;
	public static final Logger DEFAULT_LOGGER = new Logger();
	public static final String ROOT_LOGGER_NAME = "ROOT";
	public static final String NO_NAME = NullUtils.NONE;

	@XmlAttribute
	private final String name;

	@XmlElement(name = "appender-ref")
	private final List<AppenderRef> references;

	@XmlElement
	private final Level level;

	@XmlAttribute
	private final boolean additivity;

	private Logger() {
		this(NO_NAME, AppenderRef.NO_APPENDER_REFS, Level.NO_LEVEL);
	}

	public Logger(String name, Level level) {
		this(name, AppenderRef.NO_APPENDER_REFS, level, DEFAULT_ADDITIVITY);
	}

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

	public List<AppenderRef> getReferences() {
		return references;
	}

	public Boolean getAdditivity() {
		return additivity;
	}

	public String getName() {
		return name;
	}

	public Level getLevel() {
		return level;
	}

	public static Logger getRootLogger(AppenderRef reference, Level level) {
		return getRootLogger(CollectionUtils.singletonList(reference), level);
	}

	public static Logger getRootLogger(List<AppenderRef> references, Level level) {
		return new Logger(ROOT_LOGGER_NAME, references, level);
	}

	public static Logger getNoRootLogger() {
		return new Logger(ROOT_LOGGER_NAME, AppenderRef.NO_APPENDER_REFS, Level.NO_LEVEL);
	}

}
