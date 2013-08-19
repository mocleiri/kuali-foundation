package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.Null;

public final class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_CLASS = org.apache.log4j.Level.class;
	public static final Class<Null> NO_CLASS = Null.class;
	public static final Value NO_VALUE = Value.OFF;
	public static final Level NO_LEVEL = new Level();

	@XmlAttribute(name = "class")
	private final Class<?> levelClass;

	@XmlAttribute
	private final Value value;

	private Level() {
		this(NO_CLASS, NO_VALUE);
	}

	public Level(Value value) {
		this(DEFAULT_CLASS, value);
	}

	public Level(Class<?> levelClass, Value value) {
		Assert.noNulls(levelClass, value);
		this.levelClass = levelClass;
		this.value = value;
	}

	public Class<?> getLevelClass() {
		return levelClass;
	}

	public Value getValue() {
		return value;
	}

}
