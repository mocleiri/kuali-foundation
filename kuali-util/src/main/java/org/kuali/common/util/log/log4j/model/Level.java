package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.log4j.jaxb.DropLevelClassAdapter;

public final class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_CLASS = org.apache.log4j.Level.class;
	public static final Value NO_VALUE = Value.NULL;
	public static final Level NO_LEVEL = new Level();

	@XmlAttribute(name = "class")
	@XmlJavaTypeAdapter(DropLevelClassAdapter.class)
	private final Class<?> levelClass;

	@XmlAttribute
	private final Value value;

	private Level() {
		this(DEFAULT_CLASS, NO_VALUE);
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
