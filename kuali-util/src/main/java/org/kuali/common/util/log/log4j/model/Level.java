package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.log4j.jaxb.DropLevelClassAdapter;
import org.kuali.common.util.log.log4j.jaxb.ValueAdapter;

public final class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_CLASS = org.apache.log4j.Level.class;
	public static final Threshold NO_VALUE = Threshold.NULL;
	public static final Level NO_LEVEL = new Level();

	@XmlAttribute(name = "class")
	@XmlJavaTypeAdapter(DropLevelClassAdapter.class)
	private final Class<?> levelClass;

	@XmlAttribute
	@XmlJavaTypeAdapter(ValueAdapter.class)
	private final String value;

	private Level() {
		this(DEFAULT_CLASS, NO_VALUE);
	}

	public Level(Threshold value) {
		this(DEFAULT_CLASS, value);
	}

	public Level(Class<?> levelClass, Threshold value) {
		Assert.noNulls(levelClass, value);
		this.levelClass = levelClass;
		this.value = value.name().toLowerCase();
	}

	public Class<?> getLevelClass() {
		return levelClass;
	}

	public String getValue() {
		return value;
	}

}
