package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;

public final class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_JAVA_CLASS = org.apache.log4j.Level.class;
	public static final Value DEFAULT_VALUE = Value.INFO;

	private final Class<?> javaClass;
	private final Value value;

	public Level() {
		this(DEFAULT_JAVA_CLASS, Value.INFO);
	}

	public Level(Value value) {
		this(DEFAULT_JAVA_CLASS, value);
	}

	public Level(Class<?> javaClass, Value value) {
		Assert.noNulls(javaClass, value);
		this.javaClass = javaClass;
		this.value = value;
	}

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public Value getValue() {
		return value;
	}

}
