package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.log4j.jaxb.DropLevelClassAdapter;
import org.kuali.common.util.log.log4j.jaxb.LoggerAdapter;

public final class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_CLASS = org.apache.log4j.Level.class;
	public static final Level DEFAULT_LEVEL = new Level();

	@XmlAttribute(name = "class")
	@XmlJavaTypeAdapter(DropLevelClassAdapter.class)
	private final Class<?> levelClass;

	@XmlAttribute
	@XmlJavaTypeAdapter(LoggerAdapter.class)
	private final Threshold value;

	private Level() {
		this(DEFAULT_CLASS, Threshold.DEFAULT_LOGGER_VALUE);
	}

	public Level(Threshold value) {
		this(DEFAULT_CLASS, value);
	}

	public Level(Class<?> levelClass, Threshold value) {
		Assert.noNulls(levelClass, value);
		this.levelClass = levelClass;
		this.value = value;
	}

	public Class<?> getLevelClass() {
		return levelClass;
	}

	public Threshold getValue() {
		return value;
	}

}
